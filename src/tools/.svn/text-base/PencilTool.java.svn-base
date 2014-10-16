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
 * Class to construct a pencil tool.
 * 
 * @author Alex Stringham
 * @version Nov. 2011
 */
public class PencilTool extends AbstractToolParent
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
   * Starting point for pencil tool.
   * 
   * @param the_event mouse event.
   */
  public void makeShape(final MouseEvent the_event)
  {
    my_x = the_event.getX();
    my_y = the_event.getY();
  }

  /**
   * Updates the pencil after the user starts drawing.
   * 
   * @param the_event mouse event.
   * @return small segment in which the user has drawn.
   */
  public Shape update(final MouseEvent the_event)
  {
    final Line2D.Double current_scribble = new Line2D.Double(my_x,
                                                 my_y, 
                                                 the_event.getX(), 
                                                 the_event.getY());

    my_other_x = my_x;
    my_other_y = my_y;

    my_x = the_event.getX();
    my_y = the_event.getY();
    my_mouse_time++;
    return current_scribble;
  }

  /**
   * Gets the current part of the pencil drawing.
   * 
   * @return Current part of the pencil drawing.
   */
  public Shape getCurrentShape()
  {
    Line2D.Double current_pencil;

    if (my_mouse_time <= 1)
    {
      current_pencil = new Line2D.Double();
    }
    else
    {
      current_pencil = new Line2D.Double(my_x, 
                                         my_y, 
                                         my_other_x, 
                                         my_other_y);
    }

    return current_pencil;
  }

  /**
   * Finalizes the pencil drawing.
   * 
   * @param the_event mouse event.
   * @return Users final drawing.
   */
  public Shape stopDrawing(final MouseEvent the_event)
  {
    my_mouse_time = 0;
    return new Line2D.Double(my_x, my_y, my_other_x, my_other_y);
  }
}
