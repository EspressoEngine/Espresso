/* Very basic physics implementation. Look to something like https://dyn4j.org/ for a more in-depth engine. */
package com.github.jygame.physics;

import java.util.ArrayList;

import com.github.jygame.Vector2;

public class PhysicsEngine {
    public ArrayList<RigidBody> bodies = new ArrayList<RigidBody>();
    public Vector2 bounds = new Vector2();
    public double gravity = 0.05; // acceleration due to gravity

    public void nextFrame() {
        for(int i = 0; i < bodies.size(); i ++) {
            bodies.get(i).velocity.y -= gravity * bodies.get(i).mass;
            bodies.get(i).checkScreenCollisions(bounds);

            bodies.get(i).collidingBodies = 0; // Resets collidingBodies
            for(int j = 0; j < bodies.size(); j ++) {
                if(i != j) bodies.get(i).checkObjectCollisions(bodies.get(j)); // Collision detection
            }
            bodies.get(i)._onUpdate();
            
            bodies.get(i).setPosition(); // Now, after *everything*, the velocity of the object will be the velocity desired. Set the position of the object.
        }
    }

    public void add(RigidBody object) {
        bodies.add(object);
    }
    public void remove(RigidBody object) {
        bodies.remove(object);
    }

}
