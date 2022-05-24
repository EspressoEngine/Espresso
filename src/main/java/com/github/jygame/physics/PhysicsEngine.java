/* Very basic physics implementation. Look to something like https://dyn4j.org/ for a more in-depth engine. */
package com.github.jygame.physics;

import java.util.ArrayList;

import com.github.jygame.Vector2;

public class PhysicsEngine {
    public ArrayList<RigidBody> bodies = new ArrayList<RigidBody>();
    public Vector2 bounds = new Vector2();
    public double gravity = 0.05; // acceleration due to gravity

    // QUEUED CHANGES TO bodies
    private ArrayList<RigidBody> bodiesToAdd = new ArrayList<RigidBody>();
    private ArrayList<RigidBody> bodiesToRemove = new ArrayList<RigidBody>();

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

    public void add(RigidBody object) {
        bodiesToAdd.add(object);
    }
    public void remove(RigidBody object) {
        bodiesToRemove.add(object);
    }
    // --> UPDATING
    private void updateBodyList() { // We QUEUE changes instead of directly affect the object list as to avoid concurrency errors. (Hey, that's just like Scene.java!)
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
