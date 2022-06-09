package com.github.jygame;

public class Utils {
    public static double rand_range(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }

    public static boolean flip_coin() {
        return Utils.rand_range(-1, 1) > 0;
    }

    // Clamping values
    public static double clamp(double value, double min, double max) {
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }

    public static float clamp(Float value, float min, float max) {
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }

    public static int clamp(int value, int min, int max) {
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }

    // Animating numbers in steps (linearly)
    public static double stepTo(double from, double to, double step) {
        if(from > (to + step)) {
            return from - step;
        } else if(from < (to - step)) {
            return from + step;
        } else {
            return to;
        }
    }

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
