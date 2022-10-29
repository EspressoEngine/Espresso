package com.github.jygame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
import com.github.jygame.object.Mesh2D;
import com.github.jygame.object.Sprite2D;
import com.github.jygame.object.Label;

/**
 * <p>Swing <code>JPanel</code> instance where everything is drawn.</p>
 *
 * @author pastthepixels
 * @version $Id: $Id
 */
public class Canvas2D extends JPanel {
    // INSTANCE VARIABLES
    private Scene scene;

    public RenderingHints renderingHints = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

    /**
     * <p>Setter for the field <code>scene</code>.</p>
     *
     * @param scene a {@link com.github.jygame.Scene} object
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * <p>Returns true if the value <code>scene</code> is not <code>null</code>.</p>
     *
     * @return a boolean
     */
    public boolean hasScene() {
        return scene != null;
    }

    // UPDATING
    /** {@inheritDoc} */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D backend = (Graphics2D) g;
        backend.setRenderingHints(renderingHints);
        
        if (hasScene() == false) {
            return;
        }
        
        for (int i = 0; i < scene.objects.size(); i++) {
            if (scene.objects.get(i).visible == false) {
                continue; // If an object is set to be invisible, don't render that object.
            }

            // EVENTS
            scene.objects.get(i).onBeforeDraw();

            // BOUNDING BOXES I
            if(scene.objects.get(i) instanceof Mesh2D && ((Mesh2D)scene.objects.get(i)).geometry == null) ((Mesh2D)scene.objects.get(i)).updateGeometry();
            scene.objects.get(i).updateBoundingBox(backend.getTransform());

            // POSITIONING
            Vector2 centerOffset = new Vector2(scene.objects.get(i).boundingBox.getWidth()/2, scene.objects.get(i).boundingBox.getHeight()/2);
            scene.objects.get(i)._enginePosition = scene.objects.get(i).position.sub(centerOffset);

            // TRANSFORMATIONS
            AffineTransform oldTransform = backend.getTransform();
            backend.rotate(scene.objects.get(i).rotation, scene.objects.get(i).position.x, scene.objects.get(i).position.y);

            // BOUNDING BOXES II
            scene.objects.get(i).updateBoundingBox(backend.getTransform());
            
            // MESH2D
            if (scene.objects.get(i) instanceof Mesh2D) {
                Mesh2D mesh = (Mesh2D) scene.objects.get(i);
                mesh.updateGeometry();
                if (mesh.geometry != null) {
                    // Strokes stuff
                    if (mesh.strokeColor != null && mesh.strokeWidth > 0) {
                        backend.setStroke(new BasicStroke(mesh.strokeWidth));
                        backend.setColor(mesh.strokeColor);
                        backend.draw(mesh.geometry);
                    }
                    // Fills stuff
                    if (mesh.fillColor != null) {
                        backend.setColor(mesh.fillColor);
                        backend.fill(mesh.geometry);
                    }
                }

            }

            // SPRITE2D
            if (scene.objects.get(i) instanceof Sprite2D && ((Sprite2D) scene.objects.get(i)).image != null) {
                Sprite2D sprite = (Sprite2D) scene.objects.get(i);
                backend.drawImage(sprite.image, (int) sprite._enginePosition.x, (int) sprite._enginePosition.y, null);
            }
            
            // LABEL
            if (scene.objects.get(i) instanceof Label && ((Label) scene.objects.get(i)).text != null) {
                Label label = (Label) scene.objects.get(i);
                Rectangle2D textBounds = label.font.getStringBounds(label.text, backend.getFontRenderContext());
                backend.setColor(label.color);
                backend.setFont(label.font);
                backend.drawString(label.text, (int) label.position.x, (int) (label.position.y - textBounds.getY())); // Note: drawString has an origin in the bottom left. We want this in the top left.
            }

            // RESETTING TRANSFORMATIONS
            backend.setTransform(oldTransform);

            // DEBUG
            if(scene.objects.get(i).dbg == true){
                backend.setColor(Color.GREEN);
                backend.setStroke(new BasicStroke(2));
                if(scene.objects.get(i).boundingBox != null) backend.draw(scene.objects.get(i).boundingBox);
                backend.drawOval((int)scene.objects.get(i).position.x - 5, (int)scene.objects.get(i).position.y - 5, 10, 10);
            }

        }
    }

    /**
     * <p>Forces the canvas to re-draw itself.</p>
     */
    public void drawAll() {
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }
}
