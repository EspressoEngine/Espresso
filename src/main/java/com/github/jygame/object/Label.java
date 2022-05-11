package com.github.jygame.object;

import java.awt.Color;
import java.awt.Font;

public class Label extends Object {// POSITION IS FROM TOP LEFT
    public String text;

    // Material
    public Color color = Color.WHITE;
    public Font font = new Font(Font.MONOSPACED, Font.PLAIN, 24); // Default is a simple monospace font
}
