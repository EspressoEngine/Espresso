package com.github.jygame;

import java.util.ArrayList;
import java.awt.Color;
import com.github.jygame.object.Object;
import java.util.Comparator;

public class Scene {
    // INSTANCE VARIABLES
    public Window window;
    public ArrayList<Object> objects = new ArrayList<Object>();

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
        objects.add(object);
    }
    public void remove(Object object) {
        objects.remove(object);
    }
    public void sortZ() {
        objects.sort(Comparator.comparing(Object::getZIndex, (s1, s2) -> {
            return Integer.compare(s1, s2);
        }));
    }

    // FUNCTION
    public void setBackground(Color color) {
        window.canvas.setBackground(color);
    }
}
