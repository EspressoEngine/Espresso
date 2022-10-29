// DOWN == NEGATIVE
// UP == POSITIVE
// CONTRARY TO POSITIONING, WHERE DOWN == POSITIVE
package com.github.jygame.physics;

import java.util.ArrayList;

import com.github.jygame.Vector2;
import com.github.jygame.object.Object;

/**
 * <p>RigidBody class.</p>
 *
 * @author pastthepixels
 * @version $Id: $Id
 */
public class RigidBody {
    public Object object; // Object instance
    public Vector2 velocity = new Vector2(); // Uniform motion
    public double mass = 1; // Mass, measured in ??? units
    public boolean disabled = false; // Disable altering the velocity on collisions. Does not disable events.
    public ArrayList<RigidBody> collidingBodies = new ArrayList<RigidBody>(); // Keeps track of all bodies that an object is colliding with. Is reset every update.
    public ArrayList<RigidBody> oldCollidingBodies = new ArrayList<RigidBody>(); // Old value of collidingBodies each update so we can keep track of changes.
    public boolean dbg = false; // Enable debug messages

    /**
     * <p>Constructor for RigidBody.</p>
     *
     * @param object a {@link com.github.jygame.object.Object} object
     */
    public RigidBody(Object object) {
        this.object = object;
    }

    /**
     * <p>Updates the position of an object with its velocity.</p>
     */
    public void setPosition() {
        object.position = object.position.sub(this.velocity);
    }

    // Collisions
    /**
     * Checks collisions with the edges of the screen and updates the velocity of the object accordingly.
     *
     * @param bounds a {@link com.github.jygame.Vector2} object
     */
    public void checkScreenCollisions(Vector2 bounds) {
        if (object.boundingBox == null || disabled == true)
            return;

        Vector2 futurePosition = object.getBoundingBoxPosition().sub(velocity.mult(2));
        
        if (futurePosition.y + object.boundingBox.getHeight() >= bounds.y && velocity.y < 0) {
            velocity.y = 0;            
        }

        if (futurePosition.y < 0 && velocity.y > 0) {
            velocity.y = 0;
        }

        if (futurePosition.x + object.boundingBox.getWidth() >= bounds.x && velocity.x < 0) {
            velocity.x = 0;
        }

        if (futurePosition.x < 0 && velocity.x > 0) {
            velocity.x = 0;
        }
    }

    /**
     * Checks collisions with another object and updates its velocity accordingly.
     *
     * @param body a {@link com.github.jygame.physics.RigidBody} object
     */
    public void checkObjectCollisions(RigidBody body) { // ...with the bounds of the screen
        if (object.boundingBox == null)
            return;
        Vector2 futurePosition = object.getBoundingBoxPosition().sub(velocity.mult(2));
        Vector2 bodyFuturePosition = body.object.getBoundingBoxPosition().sub(body.velocity);
        if (object.boundingBox.intersects(body.object.boundingBox)
                || (futurePosition.x <= bodyFuturePosition.x + body.object.boundingBox.getWidth()
                        && futurePosition.x + object.boundingBox.getWidth() >= bodyFuturePosition.x
                        && futurePosition.y <= bodyFuturePosition.y + body.object.boundingBox.getHeight()
                        && futurePosition.y + object.boundingBox.getHeight() >= bodyFuturePosition.y)) {
            if (disabled == false) {
                if (object.boundingBox.getHeight() + futurePosition.y >= bodyFuturePosition.y && velocity.y < 0) {
                    velocity.y = 0;
                }
                if (futurePosition.y <= bodyFuturePosition.y + body.object.boundingBox.getHeight() && velocity.y > 0) {
                    velocity.y = 0;
                }

                if (object.boundingBox.getWidth() + futurePosition.x >= bodyFuturePosition.x && velocity.x < 0 ) {
                    velocity.x = 0;
                }
                if (futurePosition.x <= bodyFuturePosition.x + body.object.boundingBox.getWidth() && velocity.x > 0) {
                    velocity.x = 0;
                }
            }
            collidingBodies.add(body); // Adds 1 to the number of colliding bodies (which is again reset every update).
                                       // By the time we are done with the for loop in PhysicsEngine we should have a number of all colliding bodies.
        }

    }

    // Events
    /**
     * Called by <code>PhysicsEngine</code> when it calls <code>nextFrame</code>. Override <code>onUpdate</code> instead of this function.
     */
    public void _onUpdate() { // Simply checks for changes with oldCollidingBodies and collidingBodies and then updates oldCollidingBodies for a new update
        if(oldCollidingBodies.size() != collidingBodies.size()) {
            if(oldCollidingBodies.size() < collidingBodies.size()) {
                for(int i = 0; i < collidingBodies.size(); i ++) {
                    if(oldCollidingBodies.indexOf(collidingBodies.get(i)) == -1) {
                        onBodyEntered(collidingBodies.get(i));
                        if(dbg) System.out.println("body entered!");
                    }
                }
            } else {
                for(int i = 0; i < oldCollidingBodies.size(); i ++) {
                    if(collidingBodies.indexOf(oldCollidingBodies.get(i)) == -1) {
                        onBodyExited(oldCollidingBodies.get(i));
                        if(dbg) System.out.println("body exited!");
                    }
                }
            }
            oldCollidingBodies = new ArrayList<RigidBody>(collidingBodies);
        }
    }

    /**
     * Called when the body enters another body. Designed to be overridden.
     *
     * @param body a {@link com.github.jygame.physics.RigidBody} object
     */
    public void onBodyEntered(RigidBody body) {
        // Users can create a class inherited from this type and override this function.
        // Effectively, this becomes like an event listener.
    }
    /**
     * Called when the body exits another body. Designed to be overridden.
     *
     * @param body a {@link com.github.jygame.physics.RigidBody} object
     */
    public void onBodyExited(RigidBody body) {
        // Users can create a class inherited from this type and override this function.
        // Effectively, this becomes like an event listener.
    }
}
