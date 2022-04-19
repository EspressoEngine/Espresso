package com.github.jygame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame implements WindowListener {

  // same naming conventions used in JavaSwingTest1 because I don't want to get low marks (and I *may* be a bit lazy)
  private JPanel contentPane;

  // CONSTANTS
  final private Vector2 SIZE = new Vector2(600, 800);
  private String TITLE = "JyGame Window";

  // INITIALIZING THE WINDOW
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
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//JFrame.EXIT__ON_CLOSE
    setResizable(false);
    setBounds(0, 0, (int)SIZE.x, (int)SIZE.y);

    // creates a pane where we will hold everthing, and make it look cool
    contentPane = new JPanel();
    contentPane.setBounds(0, 0, (int)SIZE.x, (int)SIZE.y);
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
    //setContentPane(contentPane);
  }

  public void open() {
    setVisible(true);
  }

  // FUNCTIONS
  public void setBackground(Color color) {
    getContentPane().setBackground(color);
  }

  // EVENTS  
  public void windowClosed(WindowEvent e) {
   
  }

  public void windowOpened(WindowEvent e) {
    
  }
  
  public void windowClosing(WindowEvent e) {
    
  }
  
  public void windowIconified(WindowEvent e) {

  }
  
  public void windowDeiconified(WindowEvent e) {

  }
  
  public void windowActivated(WindowEvent e) {

  }
  
  public void windowDeactivated(WindowEvent e) {

  }
  
}
