// Hey! Sound not working for you?
//
// Please ensure you have an audio server installed! On my Arch Distrobox container, I had to install it using the following commands:
//  `sudo pacman -S pipewire pipewire-pulse pipewire-alsa`.
// Don't be like me! Install an audio server!
//
package com.github.jygame.sound;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    // sound
    public float volume_db = 0; // Same as playing audio in the Godot engine.
    public boolean loop = false;
    private Clip clip;

    public Sound(String path) {
        try {
            this.clip = Sound.getClip(path);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static Clip getClip(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(path);
        AudioInputStream stream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(stream);
        return clip;
    }

    public void setLoop(boolean loop) {
        clip.loop(loop == true? Clip.LOOP_CONTINUOUSLY : 0);
    }

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
