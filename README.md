# JyGame
A PyGame-inspired engine for Java using Swing.  
The goal is to make something as easy as PyGame while being object-oriented. It's a proof-of-concept for my CompSci 20 class.  
Also, it uses Swing and not something like OpenGL or Vulkan or whatever.

## How do I install JyGame for use in my own projects?
1. Clone this repository to your own computer.
2. Once in the directory, run the following command:
```bash
mvn install
```
3. The next step is to create your own Maven project.
4. In your `pom.xml` file, add this under the `<dependencies>` tag:
```xml
<dependency>
  <groupId>com.github.jygame</groupId>
  <artifactId>jygame</artifactId>
  <version>1.0</version>
</dependency>
```
5. Profit

## How do I use JyGame? Do you have an example?
Although it isn't fully documented (yet), you can try an example of JyGame [here](https://github.com/pastthepixels/JyGame-Example).
