package io.github.espressoengine.sound;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * <p>Plays sound with <code>javax.sound.sampled</code>. Right now, due to the way these libraries work, you can only use .wav files.</p>
 * For more information, look at <code>Sound.getClip</code>.
 *
 * @author pastthepixels
 * @version $Id: $Id
 */
public class Sound {
    // sound

    /*
     * Functionally same as <code>volume_db</code> from the Godot engine.
     */
    public float volume_db = 0;

    private Clip clip;

    /**
     * Constuctor that creates a new <code>Sound</code> from a file path by calling <code>Sound.getClip</code>.
     *
     * @param path a {@link java.lang.String} object
     */
    public Sound(String path) {
        try {
            this.clip = Sound.getClip(path);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a clip from a file path. Since this is built with <code>javax.sound</code> it only supports .wav files at the moment.
     *
     * @param path a {@link java.lang.String} object
     * @return a {@link javax.sound.sampled.Clip} object
     * @throws javax.sound.sampled.UnsupportedAudioFileException if any.
     * @throws java.io.IOException if any.
     * @throws javax.sound.sampled.LineUnavailableException if any.
     */
    public static Clip getClip(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(path);
        AudioInputStream stream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(stream);
        return clip;
    }

    /**
     * Sets the sound clip (<code>javax.sound.sampled.Clip</code>) to loop continuously or not.
     *
     * @param loop a boolean
     */
    public void setLoop(boolean loop) {
        clip.loop(loop == true? Clip.LOOP_CONTINUOUSLY : 0);
    }

    /**
     * Plays the sound on a new thread.
     */
    public void play() {
        // Plays audio on a different thread so we can run clip.drain()
        Executors.newSingleThreadExecutor().submit(() -> { // λ λ λ literally half-life by valve software λ λ λ
            try {
                // Waiting to ensure the clip has loaded
                while(clip == null) { Thread.sleep(100); }

                // volume
                if(clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(volume_db);
                } else {
                    System.out.println("Warning! The audio playing doesn't support its volume being changed.");
                }

                clip.setMicrosecondPosition(0); // Resets the clip
                clip.start(); // Starts playing
            } catch (Exception err) {
                err.printStackTrace();
            }
        });
    }
}
