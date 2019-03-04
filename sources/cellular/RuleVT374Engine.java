package cellular;

public class RuleVT374Engine extends Engine
{
	public RuleVT374Engine()
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
    	
    	// Count the number of adjacent live cells on a cross (five)
    	int count = 0;
    	for (int i = 1; i < 8; i += 2)
    	{
    		if (neighbors[i] == null) 
            {
                continue;
            }
    		if (neighbors[i].isState(1))
            {
                count++;
            }
        }
        // Count this cell too
        /* or not
        if (neighbors[4].isState(1)) 
        {
            count++;
        }
        /* */
        // 374 in binary, LSD first
        int[] outputs = {0, 1, 1, 0, 1, 1, 1, 0, 1, 0};
        int targetInd = 2 * count + neighbors[4].getState();
        neighbors[4].setNextState(outputs[targetInd]);
    }
}
