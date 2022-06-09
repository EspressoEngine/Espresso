package com.github.jygame;

import java.util.ArrayList;
import java.awt.Color;
import com.github.jygame.object.Object;
import java.util.Comparator;
import java.util.concurrent.Executors;

public class Scene {
    // INSTANCE VARIABLES
    public Window window;
    public ArrayList<Object> objects = new ArrayList<Object>();

    // QUEUED CHANGES TO objects
    private ArrayList<Object> objectsToAdd = new ArrayList<Object>();
    private ArrayList<Object> objectsToRemove = new ArrayList<Object>();

    // INITIALIZATION
    public Scene(Window window) {
        this.window = window;
        this.window.canvas.setScene(this);
        this.window.open();
    }
    public Scene() {
        this.window = new Window();
        this.window.canvas.setScene(this);
        this.window.open();
    }

    // OBJECTS
    public void add(Object object) {
        objectsToAdd.add(object);
    }
    public void remove(Object object) {
        objectsToRemove.add(object);
    }
    // --> UPDATING
    private void updateObjectList() { // We QUEUE changes instead of directly affect the object list as to avoid concurrency errors.
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
    private void sortZ() {
        objects.sort(Comparator.comparing(Object::getZIndex, (s1, s2) -> {
            return Integer.compare(s1, s2);
        }));
    }

    // UPDATING EVERYTHING
    public void update() {
        // a. update objects
        updateObjectList();
        // b. sort z
        sortZ();
        // c. draw
        window.canvas.drawAll();
    }

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
    public void process(double delta) { // Godot moment

    }

    // FUNCTIONS
    public void setBackground(Color color) {
        window.canvas.setBackground(color);
    }
}
