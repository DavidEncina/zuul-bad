import java.util.Stack;

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
    // Almacena al juga
    private Player jugador;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        jugador = new Player(1);
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
        entrada = new Room("en la entrada de la mazmorra", "entrada");
        salaPrincipal = new Room("en la sala principal de la mazmorra", "salaPrincipal");
        celda1 = new Room("en una celda vacia", "celda1");
        celda2 = new Room("en una celda con una cama rota", "celda2");
        pasillo = new Room("en un pasillo oscuro", "pasillo");
        armeria = new Room("en una habitacion llena de armas", "armeria");

        // initialise room exits
        // norte, este, sur, oeste, sureste, noroeste
        entrada.setExit("south", salaPrincipal, true);
        salaPrincipal.setExit("north", entrada, true);
        salaPrincipal.setExit("south", pasillo, false);
        salaPrincipal.setExit("east", celda2, true);
        salaPrincipal.setExit("west", celda1, true);
        celda1.setExit("east", salaPrincipal, true);
        celda2.setExit("west", salaPrincipal, false);
        pasillo.setExit("north", salaPrincipal, true);
        pasillo.setExit("southEast", armeria, true);
        armeria.setExit("northWest", pasillo, true);
        armeria.setExit("north", celda2, true);

        // crea un item en las habitaciones
        
        entrada.addItem(new Item("mesa", 22, false, true, false));
        entrada.addItem(new Item("piedra", 0.15f, true, true, false));
        salaPrincipal.addItem(new Item("antorcha", 1, true, true, false));
        salaPrincipal.addItem(new Item("manzana", 0.2f, true, true, true));
        salaPrincipal.addItem(new Item("jamon", 0.2f, true, false, true));
        salaPrincipal.addItem(new Item("llaveSalaPrincipal", 0.1f, true, true, false));
        celda1.addItem(new Item("hueso", 0.25f, true, true, false));
        celda1.addItem(new Item("pescado", 0.25f, true, false, true));
        celda2.addItem(new Item("escudo", 2.20f, true, true, false));
        pasillo.addItem(new Item("piedra", 0.15f, true, true, false));
        armeria.addItem(new Item("espada", 1.50f, true, true, false));        

        jugador.setRoom(entrada);  // start game outside
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
        System.out.println("Ecribe " + Option.HELP.getCommand() + " si necesitas ayuda.");
        System.out.println();
        jugador.printLocationInfo();
        System.out.println("Vida del jugador: " + jugador.getVidaActual() + "/" + jugador.getVidaMaxima());
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

        Option commandWord = command.getCommandWord();
        switch (commandWord) {
            case HELP:
            printHelp();
            break;
        
            case GO: 
            jugador.goRoom(command);
            break;

            case QUIT: 
            wantToQuit = quit(command);
            break;
        
            case LOOK: 
            jugador.printLocationInfo();
            break;
        
            case EAT:
            jugador.comerObjeto(command.getSecondWord());
            wantToQuit = jugador.getSinVida();
            break;
        
            case BACK: 
            jugador.goHabitacionAnterior();
            jugador.printLocationInfo();
            break;
        
            case TAKE: 
            jugador.cogerObjeto(command.getSecondWord());
            break;
        
            case DROP: 
            jugador.dejarObjeto(command.getSecondWord());
            jugador.printLocationInfo();
            break;
        
            case ITEMS: 
            jugador.verMochila();
            break;
        }

        return wantToQuit;
    }

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
}
