/*
 * TCSS 305 - Autumn 2011 

 * Homework 4B: Powerpaint
 * Author: Alex Stringham 
 * UWNetID: ats3216
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import tools.EllipseTool;
import tools.LineTool;
import tools.PencilTool;
import tools.RectangleTool;

/**
 * Creates the frame, panel, menu, and toolbar.
 * 
 * @author Alex
 * @version Nov.2011
 */
@SuppressWarnings("serial")
public class GuiFrame
{

  /**
   * Constant for the number 2.
   */
  public static final String DEFAULT_STROKE = "2";

  /**
   * Frame being used.
   */
  private final JFrame my_frame = new JFrame("PowerPaint");

  /**
   * Action for the color chooser.
   */
  private Action my_color_action;

  /**
   * Action for the pencil tool.
   */
  private Action my_pencil_action;

  /**
   * Action for the Line tool.
   */
  private Action my_line_action;

  /**
   * Action for the rectangle tool.
   */
  private Action my_rectangle_action;

  /**
   * Action for the Ellipse tool.
   */
  private Action my_ellipse_action;

  /**
   * Action for the clear option.
   */
  private Action my_clear_action;

  /**
   * Object to access the GuiPanel class.
   */
  private final GuiPanel my_panel = new GuiPanel();
  
  /**
   * Button group for the thicknesses.
   */
  private final ButtonGroup my_stroke_thickness = new ButtonGroup();
  /**
   * Button group for the tools.
   */
  private final ButtonGroup my_tools_menu = new ButtonGroup();

  /**
   * Button group for the tools. This is for the toolbar
   */
  private final ButtonGroup my_tool_bar = new ButtonGroup();

  /**
   * List of thicknesses avaiable.
   */
  private final List<Action> my_thickness = new ArrayList<Action>();

  /**
   * About menu Item.
   */
  private final JMenuItem my_about_option = new JMenuItem("About");

  /**
   * Grid Check Box menu item, enables and disables the on screen grid.
   */
  private final JCheckBoxMenuItem my_grid_option = new JCheckBoxMenuItem("Grid");

  /**
   * The exit action.
   */
  private Action my_exit;

  /**
   * Creates the powerpaint frame and displays it on screen.
   */
  public void generateFrame()
  {
    my_frame.setLayout(new BorderLayout());

  
    my_frame.add(createToolBar(), BorderLayout.SOUTH);
    my_frame.add(my_panel);
    my_frame.pack();

    my_frame.addWindowListener(new java.awt.event.WindowAdapter()
    {
      public void windowClosing(final WindowEvent the_event)
      {
        my_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      }
    });
    my_frame.setJMenuBar(createMenuBar());

    my_frame.setVisible(true);
  }

  /**
   * Closes window.
   */
  private void exit()
  {
    System.exit(0);
  }
  
  /**
   * Sets up the thickness.
   */
  private void thickness()
  {

    Action strokes;
    final String stroke_width = "Sets stroke width";

    strokes = new AbstractAction("1")
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        my_panel.setStrokeSize(1);
      }
    };
    strokes.putValue(Action.SHORT_DESCRIPTION, stroke_width);
    strokes.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_1);
    my_thickness.add(strokes);
    strokes = new AbstractAction(DEFAULT_STROKE)
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        my_panel.setStrokeSize(2);
      }
    };
    strokes.putValue(Action.SHORT_DESCRIPTION, stroke_width);
    strokes.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_2);
    my_thickness.add(strokes);
    strokes = new AbstractAction("4")
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        my_panel.setStrokeSize(2 * 2);
      }
    };
    strokes.putValue(Action.SHORT_DESCRIPTION, stroke_width);
    strokes.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_4);
    my_thickness.add(strokes);

  }

  /**
   * Creates a Menu bar.
   * 
   * @return the Menu bar.
   */
  @SuppressWarnings("deprecation")
  private JMenuBar createMenuBar()
  {
    final JMenuBar menubar = new JMenuBar();
    final JMenu file = new JMenu("File");
    file.setMnemonic(KeyEvent.VK_F);
    final JMenu options = new JMenu("Options");
    options.setMnemonic(KeyEvent.VK_O);
    final JMenu tools = new JMenu("Tools");
    tools.setMnemonic(KeyEvent.VK_T);
    final JMenu help = new JMenu("Help");
    help.setMnemonic(KeyEvent.VK_H);
    final JMenu thickness = new JMenu("Thickness");
    thickness.setMnemonic(KeyEvent.VK_T);

    thickness();

    for (Action a : my_thickness)
    {
      final JRadioButtonMenuItem radio_buttons = new JRadioButtonMenuItem(a);
      my_stroke_thickness.add(radio_buttons);
      thickness.add(radio_buttons);
      if (DEFAULT_STROKE.equals(radio_buttons.getLabel()))
      {
        radio_buttons.setSelected(true);
      }
    }

    file.add(my_clear_action);
    file.add(new JSeparator());
    file.add(my_exit);

    options.add(my_grid_option);
    my_grid_option.setToolTipText("Displays a grid on the panel");
    my_grid_option.setMnemonic(KeyEvent.VK_G);
    my_grid_option.addActionListener(new Listener());
    options.add(thickness);

    tools.add(my_color_action);
    tools.add(new JSeparator());

    JRadioButtonMenuItem tool_button = new JRadioButtonMenuItem(my_pencil_action);
    tools.add(tool_button);
    my_tools_menu.add(tool_button);
    tool_button.setSelected(true);

    tool_button = new JRadioButtonMenuItem(my_line_action);
    tools.add(tool_button);
    my_tools_menu.add(tool_button);

    tool_button = new JRadioButtonMenuItem(my_rectangle_action);
    tools.add(tool_button);
    my_tools_menu.add(tool_button);

    tool_button = new JRadioButtonMenuItem(my_ellipse_action);
    tools.add(tool_button);
    my_tools_menu.add(tool_button);

    my_about_option.addActionListener(new Listener());
    my_about_option.setToolTipText("View the About dialogue");
    my_about_option.setMnemonic(KeyEvent.VK_A);
    help.add(my_about_option);

    menubar.add(file);
    menubar.add(options);
    menubar.add(tools);
    menubar.add(help);

    return menubar;
  }
  
  /**
   * Creates the toolbar.
   * 
   * @return the Toolbar.
   */
  private JToolBar createToolBar()
  {

    final JToolBar toolbar = new JToolBar();

    my_color_action = new AbstractAction("Color...")
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        final Color result = JColorChooser.showDialog(null, 
                                                      "PowerPaint Color Chooser", 
                                                      my_panel.getDrawColor());
        if (result != null)
        {
          my_panel.setDrawColor(result);
        }
        else
        {
        
          my_panel.getDrawColor();
        }
      }
    };

    my_color_action.putValue(Action.SHORT_DESCRIPTION, "Choose a color to draw with");
    my_color_action.putValue(Action.ACCELERATOR_KEY,
                             KeyStroke.getKeyStroke('C', java.awt.event.KeyEvent.CTRL_MASK));
    my_color_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);


    
    final JButton button = new JButton(my_color_action);
    button.setBackground(my_panel.getDrawColor());
    
    toolbar.add(button);

    final List<Action> actions = setupActions();

    for (Action all_actions : actions)
    {
      final JToggleButton tool_bar = new JToggleButton(all_actions);
      toolbar.add(tool_bar);
      my_tool_bar.add(tool_bar);
    }

    return toolbar;

  }

  /**
   * Sets up actions for each tool.
   * 
   * @return A list of actions for all of the tools.
   */
  private List<Action> setupActions()
  {
    my_pencil_action =
        new AbstractAction("Pencil", new ImageIcon("src/gui/pencil_bw.gif"))
        {

          public void actionPerformed(final ActionEvent the_event)
          {
            my_panel.setTool(new PencilTool());
          }
        };
        
    my_pencil_action.putValue(Action.SHORT_DESCRIPTION, "The Pencil Tool");
    my_pencil_action.putValue(Action.ACCELERATOR_KEY,
                              KeyStroke.getKeyStroke('P', java.awt.event.KeyEvent.CTRL_MASK));
    my_pencil_action.putValue(Action.SELECTED_KEY, true);
    my_pencil_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);

    my_line_action = new AbstractAction("Line",
                                        new ImageIcon("src/gui/line_bw.gif"))
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        my_panel.setTool(new LineTool());
      }
    };

    
    my_line_action.putValue(Action.SHORT_DESCRIPTION, "The Line Tool");
    my_line_action.putValue(Action.ACCELERATOR_KEY,
                            KeyStroke.getKeyStroke('L', java.awt.event.KeyEvent.CTRL_MASK));
    my_line_action.putValue(Action.SELECTED_KEY, true);
    my_line_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);

    my_rectangle_action =
        new AbstractAction("Rectangle", new ImageIcon("src/gui/rectangle_bw.gif"))
        {
          public void actionPerformed(final ActionEvent the_event)
          {
            my_panel.setTool(new RectangleTool());
          }
        };

    my_rectangle_action.putValue(Action.SHORT_DESCRIPTION, "The Rectangle Tool");
    my_rectangle_action.putValue(Action.ACCELERATOR_KEY, 
                                 KeyStroke.getKeyStroke('R', 
                                 java.awt.event.KeyEvent.CTRL_MASK));
    my_rectangle_action.putValue(Action.SELECTED_KEY, true);
    my_rectangle_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);

    my_ellipse_action =
        new AbstractAction("Ellipse", new ImageIcon("src/gui/ellipse_bw.gif"))
        {
          public void actionPerformed(final ActionEvent the_event)
          {
            my_panel.setTool(new EllipseTool());
          }
        };

    my_ellipse_action.putValue(Action.SHORT_DESCRIPTION, "The Ellipse Tool");
    my_ellipse_action.putValue(Action.ACCELERATOR_KEY,
                               KeyStroke.getKeyStroke('E', 
                                                      java.awt.event.KeyEvent.CTRL_MASK));
    my_ellipse_action.putValue(Action.SELECTED_KEY, true);
    my_ellipse_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);

    my_exit = new AbstractAction("Quit", null)
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        exit();
      }
    };

    my_exit.putValue(Action.SHORT_DESCRIPTION, "Quit Powerpaint");
    my_exit.putValue(Action.ACCELERATOR_KEY,
                            KeyStroke.getKeyStroke('Q', java.awt.event.KeyEvent.CTRL_MASK));
    my_exit.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_Q);

    my_clear_action = new AbstractAction("Clear", null)
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        my_panel.clear();
      }
    };

    my_clear_action.putValue(Action.SHORT_DESCRIPTION, "Clears the drawings on the panel");
    my_clear_action.putValue(Action.ACCELERATOR_KEY,
                             KeyStroke.getKeyStroke('C', java.awt.event.KeyEvent.CTRL_MASK));
    my_clear_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);

    final List<Action> action_list = new ArrayList<Action>();

    action_list.add(my_pencil_action);
    action_list.add(my_line_action);
    action_list.add(my_rectangle_action);
    action_list.add(my_ellipse_action);

    return action_list;
  }


  /**
   * Class for the about and grid options.
   * 
   * @author Alex
   * @version Nov. 2011
   */
  private class Listener implements ActionListener
  {

   
    /**
     * {@inheritDoc}
     */
    public void actionPerformed(final ActionEvent the_event)
    {

      if (the_event.getSource() == my_grid_option)
      {
        my_panel.gridOnOff();
        
      }
      else if (the_event.getSource() == my_about_option)
      {
        JOptionPane.showMessageDialog(null, "TCSS 305 PowerPaint, V1.0");
      }
    }
  }

}
