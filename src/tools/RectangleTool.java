/*
 * TCSS 305 - Autumn 2011 

 * Homework 4B: Powerpaint
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */
package tools;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;

/**
 * Class to create a rectangle tool.
 * 
 * @author Alex Stringham
 * @version Nov. 2011
 */
public class RectangleTool extends AbstractToolParent
{

  /**
   * Initial x position.
   */
  private int my_x;
  /**
   * Initial y position.
   */
  private int my_y;

  /**
   * Counter to keep track of how long a mouse is dragged.
   */
  private int my_mouse_time;

  /**
   * Rectangle object.
   */
  private Rectangle my_rectangle;

  /**
   * Starts drawing a rectangle with 0 width and 0 height.
   * 
   * @param the_event The mouse event.
   */
  public void makeShape(final MouseEvent the_event)
  {
    my_x = the_event.getX();
    my_y = the_event.getY();
    my_rectangle = new Rectangle(my_x, my_y, 0, 0);
  }

  /**
   * Updates the rectangle via the users drawing.
   * 
   * @param the_event mouse event.
   * @return Null, because this isn't the finalized shape.
   */
  public Shape update(final MouseEvent the_event)
  {

    my_rectangle = new Rectangle(Math.min(my_x, 
                                 the_event.getX()), 
                                 Math.min(my_y, the_event.getY()), 
                                 Math.abs(my_x - the_event.getX()), 
                                 Math.abs(my_y - the_event.getY()));

    my_mouse_time++;
    return null;
  }

  /**
   * Gets the users currently drawn rectangle.
   * 
   * @return Current rectangle drawing.
   */
  public Shape getCurrentShape()
  {
    Rectangle current_rectangle = new Rectangle();
    if (my_mouse_time > 1)
    {
      current_rectangle = my_rectangle;
    }
    return current_rectangle;
  }

  /**
   * Finalizes the users rectangle.
   * 
   * @param the_event mouse event.
   * @return Finalized user drawn rectangle.
   */
  public Shape stopDrawing(final MouseEvent the_event)
  {
    my_mouse_time = 0;
    return my_rectangle;
  }
}
