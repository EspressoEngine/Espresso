package com.github.jygame.object;

import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite2D extends Object {
    // Image
    public BufferedImage image;

    public void loadImage(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch(IOException error) {
            error.printStackTrace();
        }
    }

    public void updateBoundingBox(AffineTransform transform) {
        this.boundingBox = transform.createTransformedShape(new Rectangle2D.Double(_enginePosition.x, _enginePosition.y, image.getWidth(), image.getHeight())).getBounds2D();
    }

    public Sprite2D() {

    }
    
    public Sprite2D(String path) {
        loadImage(path);
    }
}