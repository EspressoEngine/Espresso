// Hey! Sound not working for you?
//
// Please ensure you have an audio server installed! On my Arch Distrobox container, I had to install it using the following commands:
//  `sudo pacman -S pipewire pipewire-pulse pipewire-alsa`.
// Don't be like me! Install an audio server!
//
package com.github.jygame.sound;

import java.io.File;

import java.util.concurrent.Executors;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    // sound
    public double volume; // percent between 0 and 1
    private File file;

    public void loadSound(String path) {
        try {
            this.file = new File(path);
        } catch (Exception err) {
            err.printStackTrace();
        }
        
    }

    public void play(boolean loop) {
        // Plays audio on a different thread
        Executors.newSingleThreadExecutor().submit(() -> { // λ λ λ literally half-life by valve software λ λ λ
            try {
                if(file != null) {
                    // Creates a clip and opens the file
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(file));
                    //clip.loop(loop == true? Clip.LOOP_CONTINUOUSLY : 0);

                    // volume
                    if(clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                        // MATH HAPPENING
                        gainControl.setValue(((float)this.volume * (gainControl.getMaximum() + Math.abs(gainControl.getMinimum()))) - Math.abs(gainControl.getMinimum()));
                    } else {
                        System.out.println("Warning! The audio playing doesn't support its volume being changed.");
                    }


                    clip.start(); // Starts playing
                    clip.drain(); // waits till it's done
                    clip.close(); // then closes (whatever that means)
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        });
    }

    public void play() {
        this.play(false);
    }

    public Sound() {

    }

    public Sound(String path) {
        loadSound(path);
    }
}
