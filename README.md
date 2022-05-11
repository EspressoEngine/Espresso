# JyGame
A PyGame-inspired engine for Java using Swing.

The goal is to make something as easy as PyGame while being object-oriented. It's a proof-of-concept for my CompSci 20 class because it uses Swing and not something like OpenGL or Vulkan or whatever.



NOTE: I have just discovered that Java already has something like this with AWT libraries (specifically `java.awt.geom.*`). I'll thus shift the goal of this to be a wrapper for these libraries while respecting them instead of trying to bulid something over them. For instance, the geometry property of Mesh2D objects can be set to any `Shape` from `java.awt.geom.Shape`.