/*
 * TCSS 305 - Autumn 2011 

 * Homework 4B: Powerpaint
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */
package tools;

import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

/**
 * Class that creates a line tool.
 * 
 * @author Alex Stringham
 * @version Nov. 2011
 */
public class LineTool extends AbstractToolParent
{

  /**
   * Initial x coordinate.
   */
  private int my_x;
  /**
   * Initial y coordinate.
   */
  private int my_y;

  /**
   * Next x coordinate.
   */
  private int my_other_x;

  /**
   * Next y coordinate.
   */
  private int my_other_y;

  /**
   * Counter for amount of time the mouse has been dragged.
   */
  private int my_mouse_time;

  /**
   * Starting point for a line.
   * 
   * @param the_event mouse event
   */
  public void makeShape(final MouseEvent the_event)
  {
    my_x = the_event.getX();
    my_y = the_event.getY();
  }

  /**
   * Updates the line as the user is drawing it.
   * 
   * @param the_event mouse event.
   * @return Null, because the user is still drawing the shape.
   */
  public Shape update(final MouseEvent the_event)
  {
    my_other_x = the_event.getX();
    my_other_y = the_event.getY();
    my_mouse_time++;

    return null;

  }

  /**
   * Gets the shape to be drawn.
   * 
   * @return Line shape as its being drawn.
   */
  public Shape getCurrentShape()
  {
    Line2D.Double current_line;

    if (my_mouse_time <= 1)
    {
      current_line = new Line2D.Double();
    }
    else
    {
      current_line = new Line2D.Double(my_x, my_y, my_other_x, my_other_y);
    }

    return current_line;
  }

  /**
   * Finalizes the users drawing so that they can draw a new
   * line.
   * 
   * @param the_event mouse event.
   * @return Final user drawn line.
   */
  public Shape stopDrawing(final MouseEvent the_event)
  {
    my_mouse_time = 0;
    return new Line2D.Double(my_x, my_y, my_other_x, my_other_y);
  }
}
