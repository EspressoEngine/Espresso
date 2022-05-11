package com.github.jygame.object;

import com.github.jygame.Vector2;

import java.awt.geom.Rectangle2D;

public class Rect extends Mesh2D {
    public Vector2 size = new Vector2(36, 36);

    public Rectangle2D _geometry = new Rectangle2D.Double(_enginePosition.x, _enginePosition.y, size.x, size.y);
    
    public void updateGeometry() {
        this._geometry.setRect(_enginePosition.x, _enginePosition.y, size.x, size.y);
        this.geometry = this._geometry;
    }
}
