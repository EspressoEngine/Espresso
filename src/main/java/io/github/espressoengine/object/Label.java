package com.github.jygame.object;

import java.awt.Color;
import java.awt.Font;

/**
 * A simple text label.
 *
 * @author pastthepixels
 * @version $Id: $Id
 */
public class Label extends Object {// POSITION IS FROM TOP LEFT
    public String text;

    // Material

    /*
     * The color of the label.
     */
    public Color color = Color.WHITE;

    /*
     * The font that the label uses.
     */
    public Font font = new Font(Font.MONOSPACED, Font.PLAIN, 24); // Default is a simple monospace font
}
