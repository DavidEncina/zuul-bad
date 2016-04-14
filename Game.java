/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, salaPrincipal, celda1, celda2, pasillo, armeria;

        // create the rooms
        entrada = new Room("en la entrada de la mazmorra");
        salaPrincipal = new Room("en la sala principal de la mazmorra");
        celda1 = new Room("en una celda vacia");
        celda2 = new Room("en una celda con una cama rota");
        pasillo = new Room("en un pasillo oscuro");
        armeria = new Room("en una habitacion llena de armas");

        // initialise room exits
        // norte, este, sur, oeste, sureste, noroeste
        entrada.setExit("south", salaPrincipal);
        salaPrincipal.setExit("north", entrada);
        salaPrincipal.setExit("south", pasillo);
        salaPrincipal.setExit("east", celda2);
        salaPrincipal.setExit("west", celda1);
        celda1.setExit("east", salaPrincipal);
        celda2.setExit("west", salaPrincipal);
        pasillo.setExit("north", salaPrincipal);
        pasillo.setExit("southEast", armeria);
        armeria.setExit("northWest", pasillo);
        armeria.setExit("north", celda2);
        
        // crea un item en las habitaciones
        entrada.creaItem("piedra", 0.20f);
        salaPrincipal.creaItem("antorcha", 0.50f);
        celda1.creaItem("hueso", 0.25f);
        celda2.creaItem("escudo", 2.20f);
        pasillo.creaItem("piedra", 0.15f);
        armeria.creaItem("espada", 1.50f);        

        currentRoom = entrada;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Gracias por jugar. Vuelve pronto.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenido al Mundo de Zuul!");
        System.out.println("El Mundo de Zuul es un nuevo juego, y es una aventura muy aburrida.");
        System.out.println("Ecribe 'help' si necesitas ayuda.");
        System.out.println();
        printLocationInfo();
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("No entiendo lo que dices...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            System.out.println(currentRoom.getLongDescription());
        }
        else if (commandWord.equals("eat")) {
            System.out.println("You have eaten now and you are not hungry any more");
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.imprimirCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);        

        if (nextRoom == null) {
            System.out.println("Ahi no hay puerta!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
            System.out.println();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Metodo interno para imprimir las localizaciones
     */
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
    }
}
