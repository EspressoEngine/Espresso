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

    public Vector2 add(double amplitude, double direction) { // NOTE: Direction is measured in RADIANS. You can just use an object's rotation -- its reference point is the same
        return new Vector2(this.x - amplitude * Math.sin(direction), this.y + amplitude * Math.cos(direction));
    }

    public Vector2 sub(Vector2 vector) {
        return new Vector2(this.x - vector.x, this.y - vector.y);
    }

    public Vector2 mult(Vector2 vector) {
        return new Vector2(this.x * vector.x, this.y * vector.y);
    }

    public Vector2 mult(double factor) {
        return new Vector2(this.x * factor, this.y * factor);
    }

    public boolean equals(Vector2 vector) {
        return this.x == vector.x && this.y == vector.y;
    }

    public Vector2 clamp(Vector2 min, Vector2 max) { // Could move this to Utils but I think it fits better here.
        return new Vector2(
            Utils.clamp(this.x, min.x, max.x),
            Utils.clamp(this.y, min.y, max.y)
        );
    }

    public Vector2 clamp(double min, double max) {
        return new Vector2(
            Utils.clamp(this.x, min, max),
            Utils.clamp(this.y, min, max)
        );
    }

    // STRING
    public String toString() {
        return String.format("%s[x=%f, y=%f]", this.getClass().toString(), x, y);
    }
}
