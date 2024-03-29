# Espresso
A simple and lightweight engine for Java using Swing, designed primarily for beginners to Java and those who want a lightweight engine. 
The goal is to make something as easy as PyGame while being object-oriented. (I mean, it used to be called *JyGame*, after all.) It initially began as a proof-of-concept for my CompSci 20 class.

## FAQ

### Why make this even though other alternatives exist?
One of the main goals of this engine is ease-of-use. Even though other (and awesome!) engines for Java exist, I want to make something that lets beginners to Java get behind the wheel and learn Java through making video games (and other graphical software). 

### Why would you use Swing, and not OpenGL or Vulkan?
~~Because I don't know how to use either~~ It's mostly just a personal choice, and it helps that it makes the engine not rely on third-party software. Another plus is that you can build regular UI on top of your game, which is quite useful. In the future though I'm thinking about adding OpenGL/Vulkan "canvases".  

Speaking of Canvas2D, that's actually *really* similar to what I'm using to generate graphics. Java comes with Swing, which is sort of like HTML once you get the hang of it (not to mention that [you can actually use HTML in Swing](https://docs.oracle.com/javase/tutorial/uiswing/components/html.html)), and it also comes with a graphics API (under `java.awt` mostly) to tie-in to Swing. So you can sort-of think of programming a game in this engine like making an game in HTML with JavaScript.

### Was it *really* called JyGame before?
Yes.

### Why did you change it???
Mostly because I thought it might be confusing, especially to people who haven't heard of PyGame. I liked the name, but at the same time wanted to change it to something that stood independent of other software.

### So why did you choose "Espresso"?
Like the engine, it's designed to be simple and is better when you pair it with other things (like milk and sugar).  
...I guess you can't really pair this game engine with milk and sugar, but you get the point. And of course I had to name it after a coffee, because [Java](https://www.infoworld.com/article/2077265/so-why-did-they-decide-to-call-it-java.html).

## How do I install Espresso for use in my own projects?
## 1. The easy way
4. In your `pom.xml` file, add this under the `<dependencies>` tag:
```xml
<dependency>
  <groupId>io.github.espressoengine</groupId>
  <artifactId>espresso</artifactId>
  <version>1.0.0</version> <!-- Or whatever the version for the newest release is. -->
</dependency>
```
## 2. The hard way
1. Clone this repository to your own computer.
2. Once in the directory, run the following command:
```bash
mvn install
```
3. The next step is to create your own Maven project.
4. In your `pom.xml` file, add this under the `<dependencies>` tag:
```xml
<dependency>
  <groupId>io.github.espressoengine</groupId>
  <artifactId>espresso</artifactId>
  <version>1.0.0</version> <!-- Or whatever the version for the newest release is. -->
</dependency>
```
5. Profit

## How do I use Espresso? Do you have an example?
https://github.com/EspressoEngine/Example
