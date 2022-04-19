package com.github.jygame;

public class Scene {
    // INSTANCE VARIABLES
    public Window window;

    // INITIALIZATION
    public Scene(Window window) {
        this.window = window;
        this.window.open();
    }
    public Scene() {
        this.window = new Window();
        this.window.open();
    }
}
