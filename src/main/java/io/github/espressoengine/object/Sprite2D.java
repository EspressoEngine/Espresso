package io.github.espressoengine.object;

import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.imageio.ImageIO;

import io.github.espressoengine.Resource;

/**
 * <p>A 2D sprite.</p>
 *
 * @author pastthepixels
 * @version $Id: $Id
 */
public class Sprite2D extends Object {
    // Image
    public BufferedImage image;

    /**
     * <p>Loads an image from a file path.</p>
     *
     * @param resource
     */
    public void loadImage(Resource resource) {
        try {
            image = ImageIO.read(resource.getStream());
        } catch(IOException error) {
            error.printStackTrace();
        }
    }

    /** {@inheritDoc} */
    public void updateBoundingBox(AffineTransform transform) {
        this.boundingBox = transform.createTransformedShape(new Rectangle2D.Double(_enginePosition.x, _enginePosition.y, image.getWidth(), image.getHeight())).getBounds2D();
    }

    /**
     * <p>Constructor for Sprite2D.</p>
     */
    public Sprite2D() {

    }
    
    /**
     * <p>Constructor for Sprite2D with a file path for an image<p>
     *
     * @param path a {@link java.lang.String} object
     */
    public Sprite2D(String path) {
        loadImage(path);
    }
}
