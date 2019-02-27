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
    private Engine runner;
    // Default grids are a good idea, but I can just use saves later.
    // private Grid[] defaultGrids;
    /**
     *  Makes a new Automata spec with all parameters.
     */
    public Automata(String name, int maxState, int cellDim, 
        Engine runner)
    {
        this.name = name;
        this.maxState = maxState;
        this.cellDim = cellDim;
        this.runner = runner;
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
    
    /**
     *  Gets the Engine that determines this automata's rule.
     */
    public Engine getEngine()
    {
        return this.runner;
    }
    // No setters, just make a new one if that's necessary.
}
