package io.github.espressoengine.sound;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;

import java.util.concurrent.ExecutorService;

import java.io.InputStream;
import java.io.ByteArrayInputStream;

import javax.sound.sampled.*;
import javax.sound.sampled.LineEvent.Type;

/**
 * Plays sound with <code>javax.sound.sampled</code>. See <code href=
 * "https://docs.oracle.com/javase/8/docs/api/javax/sound/sampled/AudioSystem.html#getAudioFileTypes">AudioSystem.getAudioFileTypes</code>
 * for more.
 * 
 * For more information, look at <code>Sound.getClip</code>.
 *
 * @author pastthepixels
 * @version $Id: $Id
 */
public class Sound {
	public byte[] bytes;
	public AudioFormat format;
	public String url;

	public boolean loop = false;
	public float volume_db = 0;

	public ExecutorService playing_executor = Executors.newSingleThreadExecutor();

	/**
	 * Constuctor that creates a new <code>Sound</code> from a file path by calling
	 * <code>Sound.getClip</code>.
	 *
	 * @param path a {@link java.lang.String} object
	 */
	public Sound(String path) {
		this.load(path);
	}

	/**
	 * Gets an AudioInputStream from a file. Since this is built with
	 * <code>javax.sound</code> it only supports .wav files at the moment.
	 *
	 * @param file a {@link java.io.File} object
	 * @return a {@link javax.sound.sampled.AudioInputStream} object
	 * @throws javax.sound.sampled.UnsupportedAudioFileException if any.
	 * @throws java.io.IOException                               if any.
	 * @throws javax.sound.sampled.LineUnavailableException      if any.
	 */
	public static AudioInputStream getStream(File file)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		return AudioSystem.getAudioInputStream(file);
	}

	/**
	 * Loads an array of bytes from a file and caches it, so a new AudioInputStream
	 * can be created from it later without lag. If a large enough file is loaded,
	 * it can't be stored with a byte array and so it'll be loaded when it's being
	 * played.
	 * 
	 * @param path a {@link java.lang.String} object
	 */
	public void load(String path) {
		this.url = path;
		try {
			AudioInputStream stream = getStream(new File(path));
			this.bytes = stream.readAllBytes();
			this.format = stream.getFormat();
		} catch (IOException io_err) {
			System.out.println(
					"Reached IOException while loading audio--likely because it is too large. Will instead opt to load audio file when playing.");
			System.out.println(url);
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	/*
	 * Plays a clip from an AudioInputStream on a new thread with a LineListener to
	 * automatically close the clip once it is done. Thanks StackOverflow users
	 * [McDowell and
	 * others](https://stackoverflow.com/questions/577724/trouble-playing-wav-in-
	 * java/577926#577926)! You single-handedly saved me from a lot of headaches.
	 * 
	 * @param audioInputStream a {@link javax.sound.sampled.AudioInputStream} object
	 */
	private void playClip(AudioInputStream audioInputStream)
			throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		class AudioListener implements LineListener {
			private boolean done = false;

			@Override
			public synchronized void update(LineEvent event) {
				Type eventType = event.getType();
				if (eventType == Type.STOP || eventType == Type.CLOSE) {
					done = true;
					notifyAll();
				}
			}

			public synchronized void waitUntilDone() throws InterruptedException {
				while (!done) {
					wait();
				}
			}
		}

		AudioListener listener = new AudioListener();

		try {
			Clip clip = AudioSystem.getClip();
			if (this.loop) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			clip.addLineListener(listener);
			clip.open(audioInputStream);
			try {
				clip.start();
				listener.waitUntilDone();
			} finally {
				clip.close();
			}
		} finally {
			audioInputStream.close();
		}
	}

	/*
	 * Plays a preloaded file from a byte array and
	 * <code>javax.sound.sampled.AudioFormat</code> instance stored in a class
	 * instance.
	 */
	private void playPreloadedFile()
			throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		this.playClip(new AudioInputStream((InputStream) (new ByteArrayInputStream(this.bytes)), this.format,
				this.bytes.length));
	}

	/*
	 * Loads an audio file and plays it from <code>Sound.url</code>.
	 */
	private void loadAndPlay()
			throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		this.playClip(getStream(new File(this.url)));
	}

	/*
	 * Plays an audio file on a new thread and decides whether to run
	 * <code>playPreloadedFile()</code< or <code>loadAndPlay()</code>.
	 */
	public void play() {
		this.playing_executor = Executors.newSingleThreadExecutor();
		this.playing_executor.submit(() -> {
			try {
				if (this.bytes != null) {
					this.playPreloadedFile();
				} else {
					this.loadAndPlay();
				}
			} catch (Exception err) {
				err.printStackTrace();
			}
		});
	}

	/*
	 * Stops the most recently playing audio thread.
	 */
	public void stop() {
		this.playing_executor.shutdown();
	}

}