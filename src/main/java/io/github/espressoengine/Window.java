package io.github.espressoengine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Window created with <code>java.awt</code> and <code>javax.swing<code> and featuring a <code>Canvas2D</code> instance.
 *
 * @author pastthepixels
 * @version $Id: $Id
 */
public class Window extends JFrame {

  public Canvas2D canvas = new Canvas2D();

  // CONSTANTS
  final private Vector2 SIZE = new Vector2(600, 800);
  private String TITLE = "JyGame Window";
  public boolean stopOnClose = true;

  // INITIALISING THE WINDOW
  /**
   * <p>Constructor for Window.</p>
   */
  public Window() {
    init();
  }

  /**
   * <p>Constructor for Window taking a <code>Vector2</code> as a parameter for the windows's size.</p>
   *
   * @param size a {@link io.github.espressoengine.Vector2} object
   */
  public Window(Vector2 size) {
    this.SIZE.set(size);
    init();
  }

  /**
   * <p>Constructor for Window with a title.</p>
   *
   * @param title a {@link java.lang.String} object
   */
  public Window(String title) {
    this.TITLE = title;
    init();
  }

  /**
   * <p>Constructor for Window with both a size and title specified.</p>
   *
   * @param size a {@link io.github.espressoengine.Vector2} object
   * @param title a {@link java.lang.String} object
   */
  public Window(Vector2 size, String title) {
    this.SIZE.set(size);
    this.TITLE = title;
    init();
  }

  /**
   * <p>Creates a new window with <code>javax.swing</code> and <code>java.awt</code>.</p>
   */
  public void init() {
    // sets some properties of the frame
    setTitle(TITLE);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// JFrame.EXIT__ON_CLOSE
    addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent event) {
            close();
        }
    });
    setResizable(false);

    // creates a pane where we will hold everything, and make it look cool
    canvas.setPreferredSize(new Dimension((int)SIZE.x, (int)SIZE.y));
    add(canvas);

    pack();
  }

  /**
   * Displays the window on the screen.
   */
  public void open() {
    setVisible(true);
  }

  /**
   * Closes the window. If <code>Window.stopOnClose</code> is set to <code>true</code>, it will also stop the entire program.
   */
  public void close() {
    dispose();
    if (this.stopOnClose == true) System.exit(0);
  }

}
