package cellular;

public class LifeCell extends Cell
{
	public LifeCell()
	{
		super(0);
	}
	public LifeCell(int state)
	{
		super(state);
	}
	/**
	 *  Computes the next state of the Cell from the rule for this type of Cell.
	 */
    public void computeNextState(Cell[] neighbors)
    {
    	// Count the number of adjacent live cells.
    	int count = 0;
    	for (int i = 0; i < 9; i++)
    	{
    		if (i == 4 || neighbors[i] == null) continue;
    		if (neighbors[i].isState(1))
    		{
    			count++;
    		}
    	}
    	if (count < 2 || count > 3)
    	{
    		this.setNextState(0);
    	}
    	else if (count == 3)
    	{
    		this.setNextState(1);	
    	}
    	else
    	{
    		this.setNextState(this.getState());	
    	}
    }
}
