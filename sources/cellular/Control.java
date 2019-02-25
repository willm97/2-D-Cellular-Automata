package cellular;

import java.util.Scanner;

import java.util.Map;
import java.util.HashMap;

public class Control
{
	private static Map<String, Program> programs;
    private static AutomataLoader typeLoader;
	public static void main()
	{
		programs = new HashMap<String, Program>();
        typeLoader = new AutomataLoader();
		FilingCabinet filer = new FilingCabinet("text/");
		filer.printFile("welcome.txt");
		String[] commands = new String[10];
		commands[0] = "";
		while (!commands[0].equals("exit"))
		{
			// Clear the commands array
			for (int i = 0; i < 10; i++)
			{
				commands[i] = null;	
			}
			// Ask for commands
			System.out.print("Command: ");
			Scanner scan = new Scanner(System.in);
			String line = scan.nextLine();
			Scanner reader = new Scanner(line);
			// Record the commands
            int index;
			for (index = 0; index < 9 && reader.hasNext(); index++)
            {
				commands[index] = reader.next();
			}
			if (commands[0] == null
				|| commands[0] == "")
			{
				continue;
			}
			// Parse the commands -- should be better
			if (commands[0].equals("createBox"))
			{
				if (index < 4)
				{
					System.out.println("Not enough arguments!");
				}
				else
				{
					createBox(commands);
				}
			}
			else if (commands[0].equals("createProgram"))
			{
				if (index < 2)
				{
					System.out.println("Not enough arguments!");
				}
				else
				{
					createProgram(commands);
				}
			}
			else if (commands[0].equals("run"))
			{
				if (index == 0)
				{
					System.out.println("Specify Name!");
				}
				else if (index == 1)
				{
					System.out.println("Specify Box!");
				}
				else if (index == 2)
				{
					System.out.println("Specify number of ticks!");
				}
				else
				{
					run(commands);
				}
			}
			else if (commands[0].equals("save"))
			{
				if (index < 2)
				{
					System.out.println("Not enough arguments!");
				}
				else 
				{
					save(commands);	
				}
			}
            else if (commands[0].equals("load"))
            {
                if (index < 2)
                {
                    System.out.println("Not enough arguments!");
                }
                else
                {
                    load(commands);
                }
            }
			else
			{
				System.out.println("Unrecognized Command!");	
			}
		}
		filer.printFile("exit.txt");
	}
	/**
	 *  Creates a new program and adds it to the list.
	 */
	public static void createProgram(String[] input)
	{
        // AutomataLoader is a bit better!
		if (!typeLoader.hasAutomata(input[2]))
		{
			System.out.println("No automata of that type!");
		}
		else
		{
			if (!programs.containsKey(input[1]))
			{
                Program prgm = new Program(input[1], 
                    typeLoader.getAutomata(input[2]));
				programs.put(input[1], prgm);
			}
			else
			{
				System.out.println("Already a Program of that name!");
			}
		}
	}
	/**
	 *  Creates a new Box for a given program with a given name.
	 */
	public static void createBox(String[] input)
	{
		System.out.println("Creating New Box...");
		if (!programs.containsKey(input[1]))
        {
            System.out.println("Program name not found!");
        }
        else if (programs.get(input[1]).hasBoxOfName(input[2]))
        {
            System.out.println("Name taken!");
        }
        else
		{
            Program prgm = programs.get(input[1]);
			int X = Integer.parseInt(input[3]);
			int Y = Integer.parseInt(input[4]);
			prgm.addBox(input[2], X, Y);
		}
	}
	/**
	 *  Runs a grid in the specified program.
	 */
	public static void run(String input[])
	{
		if (programs.containsKey(input[1]))
		{
			programs.get(input[1]).run(input[2], 
                Integer.parseInt(input[3]));
		}
		else
		{
			System.out.println("Program not found");
		}
	}
	/**
	 *  Saves a Grid to a file.
	 */
	public static void save(String[] input)
	{
		if (programs.containsKey(input[1]))
		{
			programs.get(input[1]).saveToFile(input[2]);
		}
		else
		{
			System.out.println("Program not found");
		}
	}
	/**
	 *  Loads a Grid from a file into a new Grid.
	 */
	public static void load(String[] input)
	{
		System.out.println("PRETTY PLZ match the program type " + 
            "with load!");
		if (programs.containsKey(input[1]))
		{
			programs.get(input[1]).addBox(input[2]);
		}
		else
		{
			System.out.println("Program not found");
		}
	}
}
