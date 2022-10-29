/* Very basic physics implementation. Look to something like https://dyn4j.org/ for a more in-depth engine. */
package io.github.espressoengine.physics;

import java.util.ArrayList;

import io.github.espressoengine.Vector2;

/**
 * <p>A simple 2D physics engine for JyGame.</p>
 *
 * @author pastthepixels
 * @version $Id: $Id
 */
public class PhysicsEngine {
    public ArrayList<RigidBody> bodies = new ArrayList<RigidBody>();
    public Vector2 bounds = new Vector2();
    public double gravity = 0.05; // acceleration due to gravity

    // QUEUED CHANGES TO bodies
    private ArrayList<RigidBody> bodiesToAdd = new ArrayList<RigidBody>();
    private ArrayList<RigidBody> bodiesToRemove = new ArrayList<RigidBody>();

    /**
     * Updates the physics engine to the "next frame". Needs to be called manually every loop.
     */
    public void nextFrame() {
        updateBodyList();
        for(int i = 0; i < bodies.size(); i ++) {
            RigidBody body = bodies.get(i);
            body.velocity.y -= gravity * body.mass;
            body.checkScreenCollisions(bounds);
            body.collidingBodies.clear(); // Resets collidingBodies
            for(int j = 0; j < bodies.size(); j ++) {
                if(i != j) body.checkObjectCollisions(bodies.get(j)); // Collision detection
            }
            body._onUpdate();
            
            body.setPosition(); // Now, after *everything*, the velocity of the object will be the velocity desired. Set the position of the object.
        }
    }

    /**
     * <p>Adds a <code>RigidBody</code> to the list of total bodies.</p>
     *
     * @param object a {@link io.github.espressoengine.physics.RigidBody} object
     */
    public void add(RigidBody object) {
        bodiesToAdd.add(object);
    }

    /**
     * <p>Removes a <code>RigidBody</code> from the list of total bodies.</p>
     *
     * @param object a {@link io.github.espressoengine.physics.RigidBody} object
     */
    public void remove(RigidBody object) {
        bodiesToRemove.add(object);
    }

    /**
     * Tells the engine to update the working list of bodies.
     * We QUEUE changes (<code>add</code>/<code>remove</code>) instead of directly affect the object list as to avoid concurrency errors. (Hey, that's just like Scene.java!)
     *
     */
    private void updateBodyList() {
        // Adding bodies
        for(int i = 0; i < bodiesToAdd.size(); i++) {
            if(bodies.indexOf(bodiesToAdd.get(i)) == -1) bodies.add(bodiesToAdd.get(i)); // Avoiding adding bodies twice
        }

        // Removing bodies
        for(int i = 0; i < bodiesToRemove.size(); i++) {
            bodies.remove(bodiesToRemove.get(i));
        }

        // Clearing the arrays
        bodiesToAdd.clear();
        bodiesToRemove.clear();
    }

}
