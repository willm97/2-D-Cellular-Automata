package cellular;

import java.util.Random;
import java.io.IOException;

public class Grid
{
	private Cell[][] tiles;
	private int generation;
	private Engine runner;
	/**
	 *  Creates a new Grid with the given dimensions, and the given
	 *  type of automata.
	 */
	public Grid(int dimX, int dimY, Automata type)
	{
		this(new int[dimX][dimY], type);
        String name = type.getName();
        int midX = (dimX - dimX % 2) / 2;
        int midY = (dimY - dimY % 2) / 2;
        // The following should really be implemented with saves...
        
		// DEFAULT GRID SET UPS
		// Test requires no default set up
		// DEBUG FOR LIFE
		// A GLIDER.
        /* */
		if ("life".equals(name))
		{
            /* *
			tiles[2][2] = new Cell(1);
			tiles[3][3] = new Cell(1);
			for (int i = 1; i < 4; i++)
			{
				tiles[i][4] = new Cell(1);
			}
            /* */
            Random rand = new Random(System.currentTimeMillis());
            for (int i = -19; i < 20; i++)
            {
                for (int j = -19; j < 20; j++)
                {
                    tiles[midX + i][midY + j].setState(rand.nextInt(2));
                }
            }
            /* */
		}
		// DEBUG FOR LANGTON'S ANT
		// THE ANT ALONE.
		else /* */ if ("ant".equals(name))
		{
			tiles[dimX / 2][dimY / 2] = new Cell(4);
		}
		// DEBUG FOR WIRE WORLD
		// A LOOPING WIRE
		else if ("wire".equals(name))
		{
			for (int i = 0; i < 7; i++)
			{
				tiles[10 + i][10].setState(3);
				tiles[12 - i][12].setState(3);
				tiles[10][10 + i].setState(3);
				tiles[12][12 - i].setState(3);
			}
			tiles[11][10].setState(2);
			tiles[10][10].setState(1);
			tiles[10][11].setState(1);
		}
        // DEBUG FOR 1D RULES
        else if ("rule30".equals(name) 
                || "rule90".equals(name))
        {
            // One dot to test directionality.
            //tiles[midX][midY].setState(1);
            // One dot in middle top
            // tiles[midX][0].setState(1);
            // One dot in top right
            // tiles[dimX - 1][0].setState(1);
            // Randomized first gen for any 1D automata
            /* */
            Random rand = new Random(System.currentTimeMillis());
            for (int i = 0; i < dimX; i++)
            {
                tiles[i][0] = new Cell(rand.nextInt(2));
            }
            /* */
        }
        else if ("rule110".equals(name))
        {
            // One dot on far left
            tiles[dimX - 1][0].setState(1);
        }
        else
        {
            // One dot for simple evolution
            // tiles[midX][midY].setState(1);
            /*  */
            // randomize a central area
            Random rand = new Random(System.currentTimeMillis());
            for (int i = -2; i < 3; i++)
            {
                for (int j = -2; j < 3; j++)
                {
                    tiles[midX + i][midY + j].setState(rand.nextInt(2));
                }
            }
            /* */
        }
	}
    /**
	 *  Creates a new Grid with the given information from a loaded
     *  array and automata. Useful when loading from a file.
	 */
    public Grid(int[][] cellData, Automata type)
	{
        generation = 0;
        runner = createEngine(type);
        this.tiles = new Cell[cellData.length][cellData[0].length];
        for (int i = 0; i < tiles.length; i++)
		{
			for (int j = 0; j < tiles[i].length; j++)
			{
				this.tiles[i][j] = new Cell(cellData[i][j]);
			}
		}
    }
	/**
	 *  Returns the state of a Cell.
	 */
	public int getState(int x, int y)
	{
		return tiles[x][y].getState();
	}
	/**
	 *  Sets the state of a Cell.
	 */
	public void setState(int x, int y, int state)
	{
		tiles[x][y].setState(state);
	}
	/**
	 *  Returns the X dimension of this Grid.
	 */
	public int getDimX()
	{
		return tiles.length;
	}
	/**
	 *  Returns the Y dimension of this Grid.
	 */
	public int getDimY()
	{
		return tiles[0].length;
	}
	/**
	 *  Sets the generation count.
	 */
	public void setGeneration(int gen)
	{
		generation = gen;
	}
	/**
	 *  Gets the generation count.
	 */
	public int getGeneration()
	{
		return generation;
	}
    /**
     *  Creates an engine based on the automata.
     *  TODO break up the case statements somehow... maybe classes
     *  aren't my goto after all? But how else to show logic?
     */ 
	public Engine createEngine(Automata type)
	{
        Engine runnable = type.getEngine();
        if (runnable == null)
        {
            System.out.println("Engine for automata " + type.getName() +
                " not found in Grid!");
            return null;
        }
		return runnable;
	}
	/**
	 *  Calculates the next state of the simulation and then 
     *  changes to it.
	 */
	public void tick()
	{
		generation++;
		// Calculate the next state for each cell.
		cellNextStates();
		// Switch to the next state.
		for (int i = 0; i < tiles.length; i++)
		{
			for (int j = 0; j < tiles[i].length; j++)
			{
				tiles[i][j].setToNext();
			}
		}
	}
	/**
	 *  Calculates the next state for the automata in graceful style.
	 */
	public void cellNextStates()
	{
		Cell[] neighbors = new Cell[9];
		for (int x = 0; x < tiles.length; x++)
		{
			for (int y = 0; y < tiles[x].length; y++)
			{
				for (int i = -1; i < 2; i++)
				{
					for (int j = -1; j < 2; j++)
					{
                        if (x + i < 0 || y + j < 0 || 
                            x + i >= tiles.length || 
                            y + j >= tiles[x].length)
                        {
                            neighbors[3 * j + i + 4] = null;
                        }
                        else
                        {
                            neighbors[3 * j + i + 4] =
                                tiles[x + i][y + j];
                        }
					}
				}
				runner.computeNextState(neighbors);
			}
		}
	}
}
