package io.github.espressoengine.sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;

import io.github.espressoengine.Resource;

/*
 * Plays MIDI files. Unlike <code>io.github.espressoengine.sound.Sound</code>, we don't need to store byte arrays to play sounds multiple times because we're playing songs.
 * <b>NOTE: It has reverb and there is NOTHING you can do about it. I searched it up and got absolutely nothing. It's probably to do with a MIDI synthesizer of OpenJDK.
 * I wonder what brainiac decided that going out of their way to add <i>reverb</i> would be a good idea...</b>
 */
public class MIDIPlayer {

    private Sequencer sequencer;

	private Synthesizer synthesizer;

    public InputStream inputStream;

    /**
     * Automatically involes load() with a try-catch.
     * 
     * @param path
     */
    public MIDIPlayer(String path) {
        try {
			this.createSequencer();
            this.load(path);
        } catch(Exception err) {
            err.printStackTrace();
        }
    }

	public MIDIPlayer(Resource resource) {
        try {
			this.createSequencer();
            this.load(resource);
        } catch(Exception err) {
            err.printStackTrace();
        }
    }


    /**
     * Prints available sequnecers. Adapted from https://stackoverflow.com/questions/762803/midiunavailableexception-in-java.
     */
    public static void printSequencers() {
        MidiDevice.Info[] devices = MidiSystem.getMidiDeviceInfo();
        if (devices.length == 0) {
            System.out.println("No MIDI devices found");
        } else {
            System.out.println("Found MIDI devices:");
            for (MidiDevice.Info dev : devices) {
                System.out.println("\t" + dev);
            }
        }
    }

    /**
     * Loads a new InputStream from a file path.
     * 
     * @param path
     * @throws MidiUnavailableException
     * @throws FileNotFoundException
     */
    public void load(String path) throws MidiUnavailableException, FileNotFoundException {
        inputStream = new BufferedInputStream(new FileInputStream(new File(path)));
    }

	public void load(Resource resource) throws MidiUnavailableException, FileNotFoundException {
        inputStream = new BufferedInputStream(resource.getStream());
    }


	/**
	 * Creates a new <code>javax.sound.midi.Sequencer</code> and <code>javax.sound.midi.Synthesizer</code>
	 * @throws MidiUnavailableException
	 */
	
	public void createSequencer() throws MidiUnavailableException {
		synthesizer = MidiSystem.getSynthesizer();
		sequencer = MidiSystem.getSequencer(false);

		sequencer.open();
		synthesizer.open();

		sequencer.getTransmitter().setReceiver(synthesizer.getReceiver());
	}

	/**
	 * Loads and sets a SoundFont for the sequencer.
	 * @param resource
	 * @throws InvalidMidiDataException
	 * @throws IOException
	 * @throws MidiUnavailableException
	 */
	public void loadSoundFont(Resource resource) throws InvalidMidiDataException, IOException, MidiUnavailableException {
		synthesizer.loadAllInstruments(MidiSystem.getSoundbank(resource.getStream()));
	}

	/**
	 * Calls <code>loadSoundFont<code> with a try/catch.
	 * @param resource
	 */
	public void setSoundFont(Resource resource) {
		try {
			this.loadSoundFont(resource);
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

    /**
     * Plays sound. Note that this isn't happening on a new thread, unlike <code>io.github.espressoengine.sound.Sound</code>
     */
    public void play() {
        try {
            sequencer.open();
            sequencer.setSequence(this.inputStream);
            sequencer.start();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    /**
     * Stops the current sequencer.
     */
    public void stop() {
        sequencer.stop();
    }

    
    /**
     * Sets the sequencer to loop infinitely.
     */
    public void loopInfinitely() {
        sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
    }

    /**
     * Invokes <code>sequencer.setLoopCount</code>.
     * 
     * @param loops
     */
    public void setLoopCount(int loops) {
        sequencer.setLoopCount(loops);
    }

}