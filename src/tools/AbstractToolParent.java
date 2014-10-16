/*
 * TCSS 305 - Autumn 2011 

 * Homework 4B: Powerpaint
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */
package tools;

import java.awt.Shape;
import java.awt.event.MouseEvent;

/**
 * Parent class for all of the child tool classes.
 * 
 * @author Alex Stringham
 * @version Nov. 2011
 */
public abstract class AbstractToolParent
{

  /**
   * Starts the drawing of the selected tool.
   * 
   * @param the_event The Mouse Event.
   */
  public abstract void makeShape(MouseEvent the_event);

  /**
   * Updates the shape being drawn.
   * 
   * @param the_event Mouse evet.
   * @return Current state of the drawing.
   */
  public abstract Shape update(MouseEvent the_event);

  /**
   * Gets the selected shape tool to be drawn.
   * 
   * @return Selected tool for drawing.
   */
  public abstract Shape getCurrentShape();

  /**
   * Stops drawing the current shape so that it can draw the next one.
   * 
   * @param the_event Mouse event.
   * @return User drawn shape.
   */
  public abstract Shape stopDrawing(MouseEvent the_event);
}
