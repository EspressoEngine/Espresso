package com.github.jygame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame {

  public Canvas2D canvas = new Canvas2D();

  // CONSTANTS
  final private Vector2 SIZE = new Vector2(600, 800);
  private String TITLE = "JyGame Window";

  // INITIALISING THE WINDOW
  public Window() {
    init();
  }

  public Window(Vector2 size) {
    this.SIZE.set(size);
    init();
  }

  public Window(String title) {
    this.TITLE = title;
    init();
  }

  public Window(Vector2 size, String title) {
    this.SIZE.set(size);
    this.TITLE = title;
    init();
  }

  public void init() {
    // sets some properties of the frame
    setTitle(TITLE);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// JFrame.EXIT__ON_CLOSE
    setResizable(false);

    // creates a pane where we will hold everything, and make it look cool
    canvas.setPreferredSize(new Dimension((int)SIZE.x, (int)SIZE.y));
    add(canvas);

    pack();
  }

  public void open() {
    setVisible(true);
  }

}