package cellular;

import java.util.Map;
import java.util.HashMap;

/**
 *  A dictionary that holds the info about each automata. It literally
 *  exists just to encapsulate that function for the time being.
 *  Could be cooler if it read from a file, or procedurally produced 
 *  engines based on a ruleset (like for 1D elementary automata).
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
     *  The part that could be improved.
     */ 
    public void manualLoad()
    {
        // Each Automata I want to add hardcoded in
        myAutomatas.put("ant", new Automata(
            "ant", 10, 8, new AntEngine()));
        myAutomatas.put("life", new Automata(
            "life", 2, 2, new LifeEngine()));
        // myAutomatas.put("rule90", new Automata(
        //     "rule90", 2, 4, new Rule90Engine()));
        myAutomatas.put("test", new Automata(
            "test", 12, 8, new TestEngine()));
        myAutomatas.put("wire", new Automata(
            "wire", 4, 4, new WireEngine()));
        myAutomatas.put("ruleT736", new Automata(
            "ruleT736", 2, 1, new RuleT736Engine()));
        // Not very exciting but it does work I guess.
        myAutomatas.put("ruleVT374", new Automata(
            "ruleVT374", 2, 2, new RuleVT374Engine()));
        myAutomatas.put("sixBB", new Automata(
            "sixBB", 2, 1, new SixBBEngine()));
            
        // Ain't this fun, let's see if rule90 still behaves the same
        // Also, this technically doesn't work because it won't load
        // pictures...
        for (int i = 0; i < 256; i++)
        {
            String ruleName = "rule" + i;
            myAutomatas.put(ruleName, new Automata(
                ruleName, 2, 1, new Rule1DEngine(i)));
        }
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
