package com.github.jygame.object;

import com.github.jygame.Vector2;

import java.awt.geom.Rectangle2D;

import java.awt.geom.AffineTransform;

public class Object {
    // Position/rotation
    public Vector2 position = new Vector2();// position from CENTER
    public Vector2 _enginePosition = new Vector2(); // position from TOP LEFT
    public double rotation = 0; // radians from CENTER

    // Debugging
    public boolean dbg = false;

    // Visiblilty/z-index
    public boolean visible = true;
    public int zIndex = 1;
    public int getZIndex() { // Just for Comparator.comparing. We don't need this because zIndex is public.
        return zIndex;
    }

    // Bounding boxes
    public Rectangle2D boundingBox = new Rectangle2D.Double(0, 0, 0, 0);
    public void updateBoundingBox(AffineTransform transform) {
        // Put code here
    }

    public Vector2 getBoundingBoxPosition() {
        return new Vector2(boundingBox.getX(), boundingBox.getY());
    }

    public void lookAt(Vector2 lookpos) {
        rotation = Math.atan2(lookpos.y - position.y, lookpos.x - position.x);
    }

    // EVENTS!
    public void onBeforeDraw() { // Executed in the draw loop, before the actual drawing begins

    }

}