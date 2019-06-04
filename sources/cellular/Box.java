package cellular;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;
import javax.swing.JFrame;
import javax.imageio.*;

import java.util.Timer;
import java.util.TimerTask;

public class Box
{
	private JFrame view;
	private Grid place;
	private JPanel disp;
    private String name;
    private Automata type;
	private int width;
	private int height;
    private final int windowX;
    private final int windowY;
    private final long waitTime;
    // The WINDOW_MARGIN is the height of the window above the content.
    private static final int WINDOW_MARGIN = 22;
	/**
	 *  Creates a new Box.
	 */
	public Box(String name, Automata type, int width, int height, 
        Grid place, long waitTime)
	{
        this.name = name;
		this.type = type;
		this.width = width;
		this.height = height;
        if (place == null)
        {
            this.place = new Grid(width, height, type);
        }
        else
        {
            this.place = place;
        }
		view = new JFrame(name);
		// The following closes all the windows together...
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Adding the display JPanel
        // Currently using DrawWindow
        disp = new DrawWindow(this.place, type);
		view.add(disp);
		// Painting and revealing.
		view.pack();
		view.setVisible(true);
        this.windowX = type.getCellDim() * width;
        this.windowY = type.getCellDim() * height + WINDOW_MARGIN;
		view.setSize(windowX, windowY);
		this.waitTime = waitTime;
	}
	/**
	 *  Returns the Grid controlled by this Box.
	 */
	public Grid getGrid()
	{
		return place;
	}
	/**
	 *  Ticks the automata and updates the display.
     *  Note that any concurrency issues with running at speed or 
     *  large computation come down to this method, as view has a 
     *  distinct reference to place than this class, in a different
     *  thread!
	 */
	public void tick(long waitTime)
	{
		place.tick();
		view.repaint(waitTime, 0, 0, windowX, windowY);
		try
		{
			Thread.currentThread().sleep(waitTime);
		}
		catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
	}
	/**
	 *  Runs the Box by the given number of gens.
	 */
	public void run(int gens)
	{
		for (int i = 0; i < gens; i++)
		{
			this.tick(waitTime);
		}
	}
	/**
	 *  Returns the JPanel under this Box.
	 *  It is currently a DrawWindow.
	 */
	public JPanel getDisplayWindow()
	{
		return disp;
	}
	/**
	 *  Sets the name of the box, and updates the window title.
	 */
	public void setName(String name)
	{
		this.name = name;
		JTextField newName = new JTextField(name);
		view.setTitle(newName.getText());
	}
}
