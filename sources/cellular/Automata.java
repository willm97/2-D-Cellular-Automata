package cellular;

/**
 *  A catch-all for new automata implementations when I want to
 *  allow more flexibility. And this class explanation could use some 
 *  work I guess.
 */
public class Automata
{
    private String name;
    private int maxState;
    private int cellDim;
    // Default grids are a good idea, but I can just use saves later.
    // private Grid[] defaultGrids;
    /**
     *  Makes a new Automata spec with all parameters.
     */
    public Automata(String name, int maxState, int cellDim)
    {
        this.name = name;
        this.maxState = maxState;
        this.cellDim = cellDim;
    }
    /**
     *  Gets the name (type) of this Automata.
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     *  Gets the maximum state of this automata.
     */
    public int getMaxState()
    {
        return this.maxState;
    }
    
    /**
     *  Gets the pixel length in each cell picture for this automata.
     */
    public int getCellDim()
    {
        return this.cellDim;
    }
    // No setters, just make a new one if that's necessary.
}
