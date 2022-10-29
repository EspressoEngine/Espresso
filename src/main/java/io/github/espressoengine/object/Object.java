package io.github.espressoengine.object;

import io.github.espressoengine.Vector2;

import java.awt.geom.Rectangle2D;

import java.awt.geom.AffineTransform;

/**
 * <p>Base class for all drawable objects.</p>
 *
 * @author pastthepixels
 * @version $Id: $Id
 */
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
    /**
     * Getter for the field <code>zIndex</code>.
     * Note: Just for <code>Comparator.comparing</code>. We don't need this because zIndex is public.
     *
     * @return a int
     */
    public int getZIndex() {
        return zIndex;
    }

    // Bounding boxes
    public Rectangle2D boundingBox = new Rectangle2D.Double(0, 0, 0, 0);
    /**
     * Recreates a bounding box from an <code>java.awt.geom.AffineTransform</code>.
     * This function called every time a scene is drawn and is yet another empty function designed to be overridden.
     *
     * @param transform a {@link java.awt.geom.AffineTransform} object
     */
    public void updateBoundingBox(AffineTransform transform) {
        // Put code here
    }

    /**
     * Returns the position of an object's bounding box.
     *
     * @return a {@link io.github.espressoengine.Vector2} object
     */
    public Vector2 getBoundingBoxPosition() {
        return new Vector2(boundingBox.getX(), boundingBox.getY());
    }

    /**
     * <p>Rotates an object to point at a <code>Vector2</code></p>
     *
     * @param lookpos a {@link io.github.espressoengine.Vector2} object
     */
    public void lookAt(Vector2 lookpos) {
        rotation = Math.atan2(lookpos.y - position.y, lookpos.x - position.x);
    }

    // EVENTS!
    /**
     * <p>Executed in the draw loop, before the actual drawing begins. Designed to be overridden.</p>
     */
    public void onBeforeDraw() {

    }

}
