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
	private GameWindow game;
    private String name;
    private Automata type;
	private int width;
	private int height;
    private long waitTime;
    private final int WINDOW_X;
    private final int WINDOW_Y;
    
    private final int WINDOW_MARGIN = 20;
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
		this.waitTime = waitTime;
        /*
		int maxState = 0;
		switch (type)
		{
		case "life":
			maxState = 2;
			cellDim = 4;
			break;
		case "ant":
			maxState = 10;
			cellDim = 8;
			break;
		case "test":
			maxState = 12;
			cellDim = 8;
			break;
		case "wire":
			maxState = 4;
			cellDim = 4;
            break;
        case "rule90":
            maxState = 2;
            cellDim = 4;
            break;
        default:
            System.out.println("Type not found in Box!");
            maxState = 0;
            cellDim = 4;
            break;
		}
        */
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
		// Adding the game panel
		game = new GameWindow(this.place, type);
		view.add(game);
		// Painting and revealing.
		view.pack();
		view.setVisible(true);
		// The 20 is for the header of the window.
        this.WINDOW_X = type.getCellDim() * width;
        this.WINDOW_Y = type.getCellDim() * height + WINDOW_MARGIN;
		view.setSize(WINDOW_X, WINDOW_Y);
	}
	/**
	 *  Returns the Grid under this Box.
	 */
	public Grid getGrid()
	{
		return place;
	}
	/**
	 *  Ticks the automata and updates the display.
     *  Note that any concurrency issues with running at speed or 
     *  large computation come down to this method, as view has a 
     *  distinct reference to place than this class.
	 */
	public void tick()
	{
		place.tick();
		view.repaint(waitTime, 0, 0, WINDOW_X, WINDOW_Y);
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
	 *  Runs the Box.
	 */
	public void run(int gens)
	{
		for (int i = 0; i < gens; i++)
		{
			this.tick();
		}
	}
	/**
	 *  Returns the GameWindow JPanel under this Box.
	 */
	public GameWindow getGameWindow()
	{
		return game;
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
