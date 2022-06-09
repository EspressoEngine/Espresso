package com.github.jygame.input;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.github.jygame.Window;

import java.awt.event.KeyEvent;

public class KeyHandler {
    public boolean dbg = false; // For debugging!

    protected ArrayList<Integer> pressedKeys = new ArrayList<Integer>();

    public KeyHandler(Window window) {
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // empty :(
            }
    
            @Override
            public void keyPressed(KeyEvent e) {
                if (dbg == true) System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
                onKeyPressed(e);
                if(pressedKeys.contains(e.getKeyCode()) == false) {
                    pressedKeys.add(e.getKeyCode());
                }
            }
    
            @Override
            public void keyReleased(KeyEvent e) {
                if (dbg == true) System.out.println("Key released code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
                onKeyReleased(e);
                if(pressedKeys.contains(e.getKeyCode())) {
                    pressedKeys.remove(pressedKeys.indexOf(e.getKeyCode()));
                }
            }
        });
    }

    public ArrayList<Integer> getPressedKeys() {
        return pressedKeys;
    }

    public boolean isKeyPressed(int keyCode) {
        return pressedKeys.contains(keyCode);
    }

    public double getAxis(int negativeKeyCode, int positiveKeyCode) { // Same as Godot -- if we're doing an Input class later on to handle other input types/key binds, move this function to it
        return (isKeyPressed(positiveKeyCode)? 1 : 0) - (isKeyPressed(negativeKeyCode)? 1 : 0);
    }

    // Events. (You may override these for class instances.)
    public void onKeyPressed(KeyEvent e) {

    }

    public void onKeyReleased(KeyEvent e) {

    }
}
