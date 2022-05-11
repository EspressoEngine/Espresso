package com.github.jygame.object;

import java.awt.Color;
import java.awt.Shape;

import java.awt.geom.AffineTransform;

public class Mesh2D extends Object {
    // Geometry
    public Shape geometry;

    // Material
    public Color strokeColor; // java.awt.Color
    public int strokeWidth; // in pixels
    public Color fillColor = Color.WHITE;

    // Empty functions
    public void updateGeometry() {
        // Take in some parameters and update geometry
    }

    public void updateBoundingBox(AffineTransform transform) {
        if(this.geometry != null) this.boundingBox = transform.createTransformedShape(geometry).getBounds2D();
    }
}
