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
    	// 8 5 2
    	// 7 4 1
    	// 6 3 0
    	// Where 4 is the current cell.
        // I interpolated edges a bit.
        if (neighbors[0] == null && neighbors[6] == null)
        {
            neighbors[4].setNextState(neighbors[4].getState());
        }
        else
        {
            if (neighbors[0] == null)
            {
                neighbors[0] = new Cell(0);
            }
            if (neighbors[6] == null)
            {
                neighbors[6] = new Cell(0);
            }
            neighbors[4].setNextState(
                neighbors[0].getState() ^ neighbors[6].getState());
        }
    }
}
