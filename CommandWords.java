import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // Almacena un String como clave y objetos de tipo Option como valor
    private HashMap<String, Option> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();
        validCommands.put("go",Option.GO);
        validCommands.put("quit",Option.QUIT);
        validCommands.put("help",Option.HELP);
        validCommands.put("look",Option.LOOK);
        validCommands.put("eat",Option.EAT);
        validCommands.put("back",Option.BACK);
        validCommands.put("take",Option.TAKE);
        validCommands.put("drop",Option.DROP);
        validCommands.put("items",Option.ITEMS);
        validCommands.put("unknow",Option.UNKNOWN);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        boolean esValido = false;
        if(validCommands.containsKey(aString)) {
            esValido = true;
        }
        return esValido;
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll()
    {
        for (String comando : validCommands.keySet()) {
            System.out.println(comando);
        }
    }
}
