package cellular;

import java.util.Map;
import java.util.HashMap;

/**
 *  A dictionary that holds the info about each automata. It literally
 *  exists just to encapsulate that function for the time being.
 *  Could be cooler if it read from a file, or procedurally produced 
 *  engines based on a ruleset (like for 1D automata).
 */ 
public class AutomataLoader
{
    private Map<String, Automata> myAutomatas;
    /**
     *  Manually defines and inserts all automata. The good part about 
     *  this class is that it encapsulates this. It could read a file.
     */ 
    public void build()
    {
        myAutomatas = new HashMap<String, Automata>();
        // Each Automata I want to add hardcoded in
        myAutomatas.put("ant", new Automata("ant", 10, 8));
        myAutomatas.put("life", new Automata("life", 2, 4));
        myAutomatas.put("rule90", new Automata("rule90", 2, 4));
        myAutomatas.put("test", new Automata("test", 12, 8));
        myAutomatas.put("wire", new Automata("wire", 4, 4));
    }
    
    public boolean hasAutomata(String name)
    {
        return myAutomatas.containsKey(name);
    }
    
    /**
     *  Gets the specified Automata.
     */
    public Automata getAutomata(String name)
    {
        if (!this.hasAutomata(name))
        {
            System.out.println("No automata with that name!");
            return null;
        }
        return myAutomatas.get(name);
    }
}
