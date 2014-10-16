/*
 * TCSS 305 - Autumn 2011 

 * Homework 4B: Powerpaint
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */
package gui;

import java.awt.Color;
import java.awt.Shape;

/**
 * Class to hold drawings.
 * 
 * @author Alex Stringham
 * @version Nov. 2011
 */
public class OldDrawings
{

  /**
   * Size of the drawing being stored.
   */
  private final float my_drawing_size;

  /**
   * Users drawing that is stored.
   */
  private final Shape my_drawing;

  /**
   * Color that is being stored.
   */
  private final Color my_color;

  /**
   * Constructor, will set up and save a storage object.
   * 
   * @param the_drawing The shape being stored
   * @param the_color The Color being stored
   * @param the_drawing_size The size being stored
   */
  public OldDrawings(final float the_drawing_size, 
                           final Shape the_drawing,
                           final Color the_color)
  {
    my_drawing_size = the_drawing_size;
    my_drawing = the_drawing;
    my_color = the_color;
  }
  
  /**
   * Gets the size being stored.
   * 
   * @return Size of drawing.
   */
  public float getSize()
  {
    return my_drawing_size;
  }

  /**
   * Gets the drawing.
   * 
   * @return Drawing being stored.
   */
  public Shape getShape()
  {
    return my_drawing;
  }

  /**
   * Gets the color.
   * 
   * @return Color being stored.
   */
  public Color getColor()
  {
    return my_color;
  }



}
