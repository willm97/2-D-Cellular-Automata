package cellular;

public class Rule90Engine extends Engine
{
	public Rule90Engine()
	{
		super(2);
	}
	/**
	 *  Computes the next state of the Cell from the rule for this type.
	 */
	@Override
    public void computeNextState(Cell[] neighbors)
    {
        // Here's where the fun stuff happens!
    	// neighbors is an array of the adjacent cells, like this:
    	// 0 1 2
    	// 3 4 5
    	// 6 7 8
    	// Where 4 is the current cell.
        
        // Note values will freeze if on the upper edge
        if (neighbors[0] == null && neighbors[2] == null)
        {
            neighbors[4].setNextState(neighbors[4].getState());
            return;
        }
        // Otherwise they treat them as 0.
        if (neighbors[0] == null)
        {
            neighbors[0] = new Cell(0);
        }
        if (neighbors[2] == null)
        {
            neighbors[2] = new Cell(0);
        }
        // Computation as an XOR
        neighbors[4].setNextState(
            neighbors[0].getState() ^ neighbors[2].getState());
    }
}
