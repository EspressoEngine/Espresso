package io.github.espressoengine.object;

import io.github.espressoengine.Vector2;

import java.awt.geom.*;

/**
 * <p>Mesh2D with circle geometry.</p>
 *
 * @author pastthepixels
 * @version $Id: $Id
 */
public class Circle extends Mesh2D {
    public Vector2 size = new Vector2(36, 36);
    
    public Ellipse2D _geometry = new Ellipse2D.Double(_enginePosition.x, _enginePosition.y, size.x, size.y);
    /** {@inheritDoc} */
    public void updateGeometry() {
        this._geometry.setFrame(_enginePosition.x, _enginePosition.y, size.x, size.y);
        this.geometry = this._geometry;
    }

    /**
     * Sets the radius of the circle.
     *
     * @param radius a double
     */
    public void setRadius(double radius) {
        size.set(radius, radius);
    }
}
