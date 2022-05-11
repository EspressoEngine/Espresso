package com.github.jygame.object;

import com.github.jygame.Vector2;

import java.awt.geom.*;

public class Circle extends Mesh2D {
    public Vector2 size = new Vector2(36, 36);
    
    public Ellipse2D _geometry = new Ellipse2D.Double(_enginePosition.x, _enginePosition.y, size.x, size.y);
    public void updateGeometry() {
        this._geometry.setFrame(_enginePosition.x, _enginePosition.y, size.x, size.y);
        this.geometry = this._geometry;
    }

    public void setRadius(double radius) {
        size.set(radius, radius);
    }
}
