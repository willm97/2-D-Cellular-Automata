package cellular;

public class SixBBEngine extends Engine
{
	public SixBBEngine()
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
    	for (int i = 0; i < 9; i++)
    	{
            if (i == 4 || neighbors[i] == null)
            {
                continue;
            }
    		if (neighbors[i].isState(1))
            {
                count += i;
            }
        }
        // Only want prime counts
        boolean isPrime = true;
        for (int i = 2; i * i < count; i++)
        {
            if (count % i == 0)
            {
                isPrime = false;
                break;
            }
        }
        
        
        if (isPrime && count != 0)
        {
            neighbors[4].setNextState(1);
        }
        else
        {
            neighbors[4].setNextState(0);
        }
    }
}
