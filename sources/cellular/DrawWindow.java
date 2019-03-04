package cellular;

import java.awt.*;

import javax.swing.*;

public class DrawWindow extends JPanel
{
	private Grid block;
    private Automata type;
    private final int cellDim;
	/**
	 *  Creates a new PictureWindow with a Grid to get data from.
	 */
	public DrawWindow(Grid block, Automata type)
	{
		this.type = type;
        this.cellDim = type.getCellDim();
		this.block = block;
	}
	/**
	 *  Paints the whole window at every repaint() call.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int i = 0; i < block.getDimX(); i++)
		{
			for (int j = 0; j < block.getDimY(); j++)
			{
				int localState = block.getState(i, j);
                this.colorSwitch(g, localState);
				g.fillRect(cellDim * i, cellDim * j, cellDim, cellDim);
			}
		}
	}
    /**
     *  Selects a color to draw based on the program state.
     */ 
    public void colorSwitch(Graphics g, int colorID)
    {
        switch (colorID)
        {
            case 0:
                g.setColor(Color.WHITE);
                break;
            case 1:
                g.setColor(Color.BLACK);
                break;
            case 2:
                g.setColor(Color.RED);
                break;
            case 3:
                g.setColor(Color.MAGENTA);
                break;
            case 4:
                g.setColor(Color.ORANGE);
                break;
            case 5:
                g.setColor(Color.DARK_GRAY);
                break;
            case 6:
                g.setColor(Color.LIGHT_GRAY);
                break;
            case 7:
                g.setColor(Color.CYAN);
                break;
            case 8:
                g.setColor(Color.YELLOW);
                break;
            case 9:
                g.setColor(Color.BLUE);
                break;
            // DEBUG
            default:
                System.out.println("I DID A BROKE COLOR");
                System.exit(1);
        }
    }
}
