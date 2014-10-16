/*
 * TCSS 305 - Autumn 2011 

 * Homework 4B: Powerpaint
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */
package tools;

import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

/**
 * Class to create an ellipse on the panel.
 * 
 * @author Alex Stringham
 * @version Nov. 2011
 */
public class EllipseTool extends AbstractToolParent
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
   * Counter for the amount of time the mouse 
   * is dragged.
   */
  private int my_mouse_time;

  /**
   * The ellipse being drawn.
   */
  private Ellipse2D my_ellipse;

  /**
   * Starts an ellipse.
   * 
   * @param the_event Mouse event.
   */
  public void makeShape(final MouseEvent the_event)
  {
    my_x = the_event.getX();
    my_y = the_event.getY();
    
    my_ellipse = new Ellipse2D.Double(my_x, my_y, 0, 0);
  }

  /**
   * Updates the ellipse's current position.
   * 
   * @param the_event Mouse Event.
   * @return Null, because the shape is not final. 
   */
  public Shape update(final MouseEvent the_event)
  {
    my_ellipse = new Ellipse2D.Double(Math.min(my_x, the_event.getX()),
                                      Math.min(my_y, the_event.getY()),
                                      Math.abs(my_x - the_event.getX()),
                                      Math.abs(my_y - the_event.getY()));

    my_mouse_time++;
    return null;
  }

  /**
   * Gets the current drawing of the shape as the user has
   * drawn it. Not the final drawing.
   * 
   * @return Shape current drawing by the user.
   */
  public Shape getCurrentShape()
  {
    Ellipse2D current_ellipse = new Ellipse2D.Double();
    if (my_mouse_time > 1)
    {
      current_ellipse = my_ellipse;
    }
    return current_ellipse;
  }

  /**
   * Finalizes the users drawing so that the user can start a new
   * one.
   * 
   * @param the_event mouse event.
   * @return Shape Final user drawing.
   */
  public Shape stopDrawing(final MouseEvent the_event)
  {
    my_mouse_time = 0;
    return my_ellipse;
  }

}
