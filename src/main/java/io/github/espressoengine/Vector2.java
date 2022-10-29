package com.github.jygame;

/**
 * Represents a 2D vector.
 *
 * @author pastthepixels
 * @version $Id: $Id
 */
public class Vector2 {
    // INSTANCE VARS/CONSTRUCTOR
    public double x;
    public double y;

    /**
     * <p>Constructor for Vector2.</p>
     *
     * @param x a double
     * @param y a double
     */
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * <p>Constructor for Vector2.</p>
     */
    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    // UTILITY FUNCTIONS
    /**
     * Sets the position of the vector from two numbers.
     *
     * @param x a double
     * @param y a double
     */
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the position of the vector to that of another vector.
     *
     * @param vector a {@link com.github.jygame.Vector2} object
     */
    public void set(Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }
    
    // MATH
    /**
     * Returns a new vector with its position added from both the current vector and parameter <code>vector</code>.
     *
     * @param vector a {@link com.github.jygame.Vector2} object
     * @return a {@link com.github.jygame.Vector2} object
     */
    public Vector2 add(Vector2 vector) {
        return new Vector2(this.x + vector.x, this.y + vector.y);
    }

    /**
     * Returns a new vector with the current vector's position but moved toward a direction (in radians) by an amplitude.
     * NOTE: Direction is measured in RADIANS. You can just use an object's rotation -- its reference point is the same.
     *
     * @param amplitude a double
     * @param direction a double
     * @return a {@link com.github.jygame.Vector2} object
     */
    public Vector2 add(double amplitude, double direction) {
        return new Vector2(this.x - amplitude * Math.sin(direction), this.y + amplitude * Math.cos(direction));
    }

    /**
     * Returns a new vector with its position being the current vector's subtracted by parameter <code>vector</code>.
     *
     * @param vector a {@link com.github.jygame.Vector2} object
     * @return a {@link com.github.jygame.Vector2} object
     */
    public Vector2 sub(Vector2 vector) {
        return new Vector2(this.x - vector.x, this.y - vector.y);
    }

    /**
     * Returns a new vector whose position is the current vector's multiplied by parameter <code>vector</code>'s position.
     *
     * @param vector a {@link com.github.jygame.Vector2} object
     * @return a {@link com.github.jygame.Vector2} object
     */
    public Vector2 mult(Vector2 vector) {
        return new Vector2(this.x * vector.x, this.y * vector.y);
    }

    /**
     * Returns a new vector whose position is the current vector's multiplied by a factor.
     *
     * @param factor a double
     * @return a {@link com.github.jygame.Vector2} object
     */
    public Vector2 mult(double factor) {
        return new Vector2(this.x * factor, this.y * factor);
    }

    /**
     * Returns true if two vectors have the same position.
     *
     * @param vector a {@link com.github.jygame.Vector2} object
     * @return a boolean
     */
    public boolean equals(Vector2 vector) {
        return this.x == vector.x && this.y == vector.y;
    }

    /**
     * <p>Returns a new vector with <code>Utils.clamp</code> used on both the x and y values for the vector.</p>
     *
     * @param min a {@link com.github.jygame.Vector2} object
     * @param max a {@link com.github.jygame.Vector2} object
     * @return a {@link com.github.jygame.Vector2} object
     */
    public Vector2 clamp(Vector2 min, Vector2 max) { // Could move this to Utils but I think it fits better here.
        return new Vector2(
            Utils.clamp(this.x, min.x, max.x),
            Utils.clamp(this.y, min.y, max.y)
        );
    }

    /**
     * <p>Returns a new vector with <code>Utils.clamp</code> used on both the x and y values for the vector.</p>
     *
     * @param min a double
     * @param max a double
     * @return a {@link com.github.jygame.Vector2} object
     */
    public Vector2 clamp(double min, double max) {
        return new Vector2(
            Utils.clamp(this.x, min, max),
            Utils.clamp(this.y, min, max)
        );
    }

    // STRING
    /**
     * <p>toString.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String toString() {
        return String.format("%s[x=%f, y=%f]", this.getClass().toString(), x, y);
    }
}
