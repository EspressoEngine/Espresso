package io.github.espressoengine.object;

import io.github.espressoengine.Vector2;

import java.awt.geom.Rectangle2D;

/**
 * <p>Mesh2D with rectangle geometry.</p>
 *
 * @author pastthepixels
 * @version $Id: $Id
 */
public class Rect extends Mesh2D {
    public Vector2 size = new Vector2(36, 36);

    public Rectangle2D _geometry = new Rectangle2D.Double(_enginePosition.x, _enginePosition.y, size.x, size.y);
    
    /** {@inheritDoc} */
    public void updateGeometry() {
        this._geometry.setRect(_enginePosition.x, _enginePosition.y, size.x, size.y);
        this.geometry = this._geometry;
    }

    /**
     * <p>Constructor for Rect.</p>
     */
    public Rect() {

    }

    /**
     * <p>Constructor for Rect.</p>
     *
     * @param size a {@link io.github.espressoengine.Vector2} object
     */
    public Rect(Vector2 size) {
        this.size = size;
    }
}
