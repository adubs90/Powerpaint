/*
 * TCSS 305 - Autumn 2011 

 * Homework 4B: Powerpaint
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */
package gui;

/**
 * Main class to run Powerpaint.
 * 
 * @author Alex Stringham
 * @version Nov. 2011
 * 
 */
public final class PowerPaintMain
{
  /**
   * Makes this instantiable.
   */
  private PowerPaintMain()
  {
  }

  /**
   * Main method to start Powerpaint.
   * 
   * @param the_args Command line, not used.
   */
  public static void main(final String[] the_args)
  {
    final GuiFrame start = new GuiFrame();
    start.generateFrame();

  }
}
