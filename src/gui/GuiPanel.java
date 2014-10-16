/*
 * TCSS 305 - Autumn 2011 

 * Homework 4B: Powerpaint
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */

package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import tools.AbstractToolParent;
import tools.PencilTool;

/**
 * JPanel used for painting PowerPaint options.
 * 
 * @author Alex Stringham
 * @version Nov. 2011
 */
@SuppressWarnings("serial")
public class GuiPanel extends JPanel
{

  /**
   * The Grid spacing, used when the grid option is checked.
   */
  public static final int SPACE = 10;
  
  /**
   * Width constant.
   */
  private static final int WIDTH = 400;

  /**
   * Height constant.
   */
  private static final int HEIGHT = 300;
  
  /**
   * List of drawings.
   */
  private final List<OldDrawings> my_drawing_list = new LinkedList<OldDrawings>();
  
  /**
   * Current tool, set to pencil as default.
   */
  private AbstractToolParent my_tool = new PencilTool();

  /**
   * Current stroke size, set to 2 as default.
   */
  private float my_stroke_size = 2;

  /**
   * Checks whether the grid is on or off.
   */
  private boolean my_grid_check;
  
  
  /**
   * Current color, set to black as default.
   */
  private Color my_color = Color.BLACK;

  /**
   * Constructor for GuiPanel to set the preferred size and the
   * mouse listeners.
   */
  public GuiPanel()
  {
    super();
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.WHITE);
    addMouseListener(new MouseListener());
    addMouseMotionListener(new MouseListener());
    
  }

  /**
   * Paints what the user is drawing.
   * 
   * @param the_graphics users drawing.
   */
  public void paintComponent(final Graphics the_graphics)
  {
    super.paintComponent(the_graphics);
    final Graphics2D graphics = (Graphics2D) the_graphics;

    graphics.setStroke(new BasicStroke(my_stroke_size));
    graphics.setColor(my_color);
    graphics.draw(my_tool.getCurrentShape());

    final int width = this.getWidth();
    final int height = this.getHeight();
    
    for (int i = 0; i < my_drawing_list.size(); i++)
    {
      graphics.setColor((Color) my_drawing_list.get(i).getColor());
      graphics.setStroke(new BasicStroke((Float) my_drawing_list.get(i).getSize()));
      graphics.draw((Shape) my_drawing_list.get(i).getShape());
    }

    if (my_grid_check)
    {

      graphics.setStroke(new BasicStroke(1));
      graphics.setColor(Color.BLACK);

      for (int row = 0; row < width; row = row + SPACE)
      {
        graphics.drawLine(row, 0, row, height);
      }

      for (int column = 0; column < height; column = column + SPACE)
      {
        graphics.drawLine(0, column, width, column);
      }
    }
  }

  /**
   * Clears all drawings.
   */
  public void clear()
  {
    my_drawing_list.clear();
    repaint();
  }
  
  /**
   * Gets the current drawing color.
   * 
   * @return the current color used for drawing.
   */
  public Color getDrawColor()
  {
    return my_color;
  }
  
  /**
   * Sets the drawing color for the user. Set to Black
   * as the default.
   * 
   * @param the_color picked drawing color.
   */
  public void setDrawColor(final Color the_color)
  {
    my_color = the_color;
  }
  
  /**
   * Sets the tool for the user. Set to pencil
   * as the default.
   * 
   * @param the_tool picked tool.
   */
  public void setTool(final AbstractToolParent the_tool)
  {
    my_tool = the_tool;
  }

  /**
   * Sets the stroke size for the user. Set to 2
   * as the default.
   * 
   * @param the_stroke_size picked stroke size.
   */
  public void setStrokeSize(final int the_stroke_size)
  {
    my_stroke_size = the_stroke_size;
  }

  /**
   * Checks whether the the grid is turned on or off.
   */
  public void gridOnOff()
  {
    if (my_grid_check)
    {
      my_grid_check = false;
    }
    else
    {
      my_grid_check = true;
    }
    repaint();
  }

  /**
   * Mouse listener class to listen for various mouse events.
   */
  private class MouseListener extends MouseInputAdapter
  {

    /**
     *{@inheritDoc}
     */
    public void mousePressed(final MouseEvent the_event)
    {
      my_tool.makeShape(the_event);
      repaint();
    }

    /**
     *{@inheritDoc}
     */
    public void mouseDragged(final MouseEvent the_event)
    {
      final Shape current_tool = my_tool.update(the_event);

      if (current_tool != null)
      {
        my_drawing_list.add(new OldDrawings(my_stroke_size, current_tool, my_color));
      }
      repaint();
    }

    /**
     *{@inheritDoc}
     */
    public void mouseReleased(final MouseEvent the_event)
    {
      final Shape current_tool = my_tool.stopDrawing(the_event);
      my_drawing_list.add(new OldDrawings(my_stroke_size, current_tool, my_color));
    }
  }
}
