# JyGame
A PyGame-inspired engine for Java using Swing.  
The goal is to make something as easy as PyGame while being object-oriented. It's a proof-of-concept for my CompSci 20 class.  
Also, it uses Swing and not something like OpenGL or Vulkan or whatever.

## How do I install JyGame for use in my own projects?
### Method A: GitHub Packages
1. Create a Maven project.
2. Add this to your `pom.xml`:
```xml
<repositories>
  <repository>
    <id>github</id>
    <name>GitHub PastThePixels Apache Maven Packages</name>
    <url>https://maven.pkg.github.com/pastthepixels/JyGame</url>
    <releases><enabled>true</enabled></releases>
    <snapshots><enabled>true</enabled></snapshots>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.jygame</groupId>
    <artifactId>jygame</artifactId>
    <version>1.0</version>
  </dependency>
</dependencies>
```
3. Go [here](https://github.com/settings/tokens) and create a personal access token with the "read:packages" checkbox checked.
4. Edit your [settings.xml](https://maven.apache.org/settings.html) so it looks something like this. Make sure to replace `USERNAME` and `ACCESS_TOKEN` with your actual username and access token.
```xml
<?xml version="1.0" encoding="UTF-8"?>

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
  <servers>
      <server>
          <id>github</id>
          <username>USERNAME</username>
          <password>ACCESS_TOKEN</password>
      </server>
  </servers>
</settings>
```
5. Profit

### Method B: Build it yourself
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
