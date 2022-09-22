package com.github.jygame;

/**
 * Utility functions to help reduce the amount of code in a game.
 *
 * @author pastthepixels
 * @version $Id: $Id
 */
public class Utils {
    /**
     * Returns a random number within a range of numbers.
     *
     * @param min a double
     * @param max a double
     * @return a double
     */
    public static double rand_range(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }

    /**
     * Returns true or false, randomly.
     *
     * @return a boolean
     */
    public static boolean flip_coin() {
        return Utils.rand_range(-1, 1) > 0;
    }

    // Clamping values
    /**
     * Returns <code>min</code> if <code>value</code> is lower than <code>min</code>
     * and <code>max</code> if <code>value</code> is higher than <code>max</code>.
     *
     * @param value a double
     * @param min a double
     * @param max a double
     * @return a double
     */
    public static double clamp(double value, double min, double max) {
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }

    /**
     * Returns <code>min</code> if <code>value</code> is lower than <code>min</code>
     * and <code>max</code> if <code>value</code> is higher than <code>max</code>.
     *
     * @param value a {@link java.lang.Float} object
     * @param min a float
     * @param max a float
     * @return a float
     */
    public static float clamp(Float value, float min, float max) {
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }

    /**
     * Returns <code>min</code> if <code>value</code> is lower than <code>min</code>
     * and <code>max</code> if <code>value</code> is higher than <code>max</code>.
     *
     * @param value a int
     * @param min a int
     * @param max a int
     * @return a int
     */
    public static int clamp(int value, int min, int max) {
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }

    // Animating numbers in steps (linearly)
    /**
     * Moves a number, <code>from</code>, closer to a target, <code>to</code>, by <code>step</code> every time the function is called.
     *
     * @param from a double
     * @param to a double
     * @param step a double
     * @return a double
     */
    public static double stepTo(double from, double to, double step) {
        if(from > (to + step)) {
            return from - step;
        } else if(from < (to - step)) {
            return from + step;
        } else {
            return to;
        }
    }

    /**
     * Moves a number, <code>from</code>, closer to a target, <code>to</code>, by <code>step</code> every time the function is called.
     *
     * @param from a float
     * @param to a float
     * @param step a float
     * @return a float
     */
    public static float stepTo(float from, float to, float step) {
        if(from > (to + step)) {
            return from - step;
        } else if(from < (to - step)) {
            return from + step;
        } else {
            return to;
        }
    }
}
