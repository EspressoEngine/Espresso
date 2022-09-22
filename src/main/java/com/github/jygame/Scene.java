package com.github.jygame;

import java.util.ArrayList;
import java.awt.Color;
import com.github.jygame.object.Object;
import java.util.Comparator;
import java.util.concurrent.Executors;

/**
 * <p>Organizes and contains main JyGame components. Each scene contains a window and list of objects to draw, along with some extra functions.</p>
 *
 * @author pastthepixels
 * @version $Id: $Id
 */
public class Scene {
    // INSTANCE VARIABLES
    public Window window;
    public ArrayList<Object> objects = new ArrayList<Object>();

    // QUEUED CHANGES TO objects
    private ArrayList<Object> objectsToAdd = new ArrayList<Object>();
    private ArrayList<Object> objectsToRemove = new ArrayList<Object>();

    // INITIALIZATION
    /**
     * <p>Constructor which uses a predefined <code>Window</code>.</p>
     *
     * @param window a {@link com.github.jygame.Window} object
     */
    public Scene(Window window) {
        this.window = window;
        this.window.canvas.setScene(this);
        this.window.open();
    }
    /**
     * <p>Constructor which creates a new <code>Window</code>.</p>
     */
    public Scene() {
        this.window = new Window();
        this.window.canvas.setScene(this);
        this.window.open();
    }

    // OBJECTS

    /**
     * Adds an object to the scene (by adding it to a queue of objects to add to <code>Scene.objects</code> when the scene next updates).
     *
     * @param object a {@link com.github.jygame.object.Object} object
     */
    public void add(Object object) {
        objectsToAdd.add(object);
    }
    /**
     * Removes an object from the scene (by adding it to a queue of objects to remove from <code>Scene.objects</code> when the scene next updates).
     *
     * @param object a {@link com.github.jygame.object.Object} object
     */
    public void remove(Object object) {
        objectsToRemove.add(object);
    }

    // --> UPDATING

    /*
     * Applies changes set in <code>Scene.objectsToAdd</code> and <code>Scene.objectsToRemove</code> to <code>Scene.objects</code>.
     * This queues changes instead of directly affecting the object list as to avoid concurrency errors when the scene updates.
     */
    private void updateObjectList() {
        // Adding objects
        for(int i = 0; i < objectsToAdd.size(); i++) {
            if(objects.indexOf(objectsToAdd.get(i)) == -1) objects.add(objectsToAdd.get(i)); // Avoiding adding objects twice
        }

        // Removing objects
        for(int i = 0; i < objectsToRemove.size(); i++) {
            objects.remove(objectsToRemove.get(i));
        }

        // Clearing the arrays
        objectsToAdd.clear();
        objectsToRemove.clear();
    }

    /* 
     * Sorts the object list by the z-index of each object. If an object is at the end of the list it'll get drawn last and therefore be on top of everything else.
     */
    private void sortZ() {
        objects.sort(Comparator.comparing(Object::getZIndex, (s1, s2) -> {
            return Integer.compare(s1, s2);
        }));
    }

    /**
     * Updates the object list and redraws the canvas in the window attached to the scene.
     */
    public void update() {
        // a. update objects
        updateObjectList();
        // b. sort z
        sortZ();
        // c. draw
        window.canvas.drawAll();
    }

    /**
     * Starts a new loop in which <code>Scene.update()</code> is called on a separate thread at 60 frames per second.
     */
    public void startLoop() {
        long frequency = 1000/60; // The 60 in this case is 60 FPS;
        Executors.newSingleThreadExecutor().submit(() -> {
            while(true) {
                process(frequency);
                update();

                // Waiting
                try {
                    Thread.sleep(frequency);
                } catch (InterruptedException e) {
                }
            }
        });
    }

    // YOU CAN OVERRIDE THIS
    /**
     * Called every time the scene updates and is designed to be overridden (another function very inspired from Godot).
     *
     * @param delta a double
     */
    public void process(double delta) {

    }

    // FUNCTIONS
    /**
     * Sets the background of a scene to a specified color.
     *
     * @param color a {@link java.awt.Color} object
     */
    public void setBackground(Color color) {
        window.canvas.setBackground(color);
    }
}
