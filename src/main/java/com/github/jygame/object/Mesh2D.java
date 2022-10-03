package com.github.jygame.object;

import java.awt.Color;
import java.awt.Shape;

import java.awt.geom.AffineTransform;

/**
 * A "base" mesh class utilizing <code>java.awt.geom</code>.
 *
 * @author pastthepixels
 * @version $Id: $Id
 */
public class Mesh2D extends Object {
    // Geometry
    public Shape geometry;

    // Material
    public Color strokeColor; // java.awt.Color
    public int strokeWidth; // in pixels
    public Color fillColor = Color.WHITE;

    // Empty functions
    /**
     * Recreates the geometry of the object taking in factors like its position.
     * This function is designed to be overridden and is called automaticaly when the mesh is drawn.
     */
    public void updateGeometry() {
        // Take in some parameters and update geometry
    }

    /** {@inheritDoc} */
    public void updateBoundingBox(AffineTransform transform) {
        if(this.geometry != null) this.boundingBox = transform.createTransformedShape(geometry).getBounds2D();
    }
}
