package io.github.espressoengine.input;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import io.github.espressoengine.Window;

import java.awt.event.KeyEvent;

/**
 * Class to handle input from keyboards.
 * @author pastthepixels
 * @version $Id: $Id
 *
 */
public class KeyHandler {
    public boolean dbg = false; // For debugging!

    protected ArrayList<Integer> pressedKeys = new ArrayList<Integer>();

    /**
     * <p>Constructor for KeyHandler.</p>
     *
     * @param window a {@link io.github.espressoengine.Window} object
     */
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

    /**
     * Gets a list of all keys currently being pressed down.
     *
     * @return a {@link java.util.ArrayList} object
     */
    public ArrayList<Integer> getPressedKeys() {
        return pressedKeys;
    }

    /**
     * <p>Returns true if a keycode is in the list of pressed keys.</p>
     *
     * @param keyCode a int
     * @return a boolean
     */
    public boolean isKeyPressed(int keyCode) {
        return pressedKeys.contains(keyCode);
    }

    /**
     * <p>Similar to Godot's getAxis. Returns -1 if the key under <code>negativeKeyCode<code> is pressed, 1 if the key under <code>positiveKeyCode</code> is pressed, and 0 if none or both of them are pressed.</p>
     *
     * @param negativeKeyCode a int
     * @param positiveKeyCode a int
     * @return a double
     */
    public double getAxis(int negativeKeyCode, int positiveKeyCode) { // Same as Godot -- if we're doing an Input class later on to handle other input types/key binds, move this function to it
        return (isKeyPressed(positiveKeyCode)? 1 : 0) - (isKeyPressed(negativeKeyCode)? 1 : 0);
    }

    // Events. (You may override these for class instances.)
    /**
     * Is called whenever a key is pressed, and is designed to be overridden on class instances as an event.
     *
     * @param e a {@link java.awt.event.KeyEvent} object
     */
    public void onKeyPressed(KeyEvent e) {

    }

    /**
     * Is called whenever a key is released, and is designed to be overridden on class instances as an event.
     *
     * @param e a {@link java.awt.event.KeyEvent} object
     */
    public void onKeyReleased(KeyEvent e) {

    }
}
