package cellular;

public class Rule1DEngine extends Engine
{
    private int[] outputs;
	public Rule1DEngine(int ruleNum)
	{
		super(2);
        // Calculate the rule as a string in binary from the number.
        this.outputs = new int[8];
        // ruleNum will be consumed
        for (int i = 0; i < outputs.length; i++)
        {
            this.outputs[i] = ruleNum % 2;
            if (ruleNum % 2 == 1)
            {
                 ruleNum--;
            }
            ruleNum = ruleNum / 2;
        }
        // outputs is now initialized, but I'll check the number
        if (ruleNum != 0)
        {
            System.out.println("Something is wrong with binary!");
        }
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
        
        // Values will freeze to be the same if on the upper edge
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
        // Computation
        int targetInd = 4 * neighbors[0].getState()
            + 2 * neighbors[1].getState()
            + neighbors[2].getState();
        // output according to rule
        neighbors[4].setNextState(outputs[targetInd]);
    }
}
