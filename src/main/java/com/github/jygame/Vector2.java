package com.github.jygame;

public class Vector2 {
    // INSTANCE VARS/CONSTRUCTOR
    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    // UTILITY FUNCTIONS
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }
    
    // MATH
    public Vector2 add(Vector2 vector) {
        return new Vector2(this.x + vector.x, this.y + vector.y);
    }

    public Vector2 sub(Vector2 vector) {
        return new Vector2(this.x - vector.x, this.y - vector.y);
    }

    public Vector2 mult(Vector2 vector) {
        return new Vector2(this.x * vector.x, this.y * vector.y);
    }
}
