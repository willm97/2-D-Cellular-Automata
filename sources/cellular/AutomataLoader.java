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
    
    public AutomataLoader()
    {
        myAutomatas = new HashMap<String, Automata>();
        this.manualLoad();
    }
    
    /**
     *  Manually defines and inserts all automata. The good part.
     */ 
    public void manualLoad()
    {
        // Each Automata I want to add hardcoded in
        myAutomatas.put("ant", new Automata("ant", 10, 8));
        myAutomatas.put("life", new Automata("life", 2, 4));
        myAutomatas.put("rule90", new Automata("rule90", 2, 4));
        myAutomatas.put("test", new Automata("test", 12, 8));
        myAutomatas.put("wire", new Automata("wire", 4, 4));
    }
    
    /**
     *  Checks if the given name matches a known automata.
     */ 
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
