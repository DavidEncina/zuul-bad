import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southEastExit;
    private Room nothWestExit;
    private HashMap<String, Room> salidas;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        salidas = new HashMap<String, Room>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southEast, Room northWest) 
    {
        if(north != null)
            northExit = north;
            salidas.put("north", north);        
        if(east != null)
            eastExit = east;
            salidas.put("east", east);
        if(south != null)
            southExit = south;
            salidas.put("south", south);
        if(west != null)
            westExit = west;
            salidas.put("west", west);
        if(southEast != null)
            southEastExit = southEast;
            salidas.put("southEast", southEast);
        if(northWest != null)
            nothWestExit = northWest;
            salidas.put("northWest", northWest);
    }

    /**
     * Devuelve la habitacion a la que se puede salir desde la direccion introducida por parametro.
     * Si no hay ningula devuelve null.
     */
    public Room getExit (String direction)
    {
        Room habitacionConSalida = null;
        if(direction.equals("north")) {
            habitacionConSalida = salidas.get("north");
        }
        if(direction.equals("east")) {
            habitacionConSalida = salidas.get("east");
        }
        if(direction.equals("south")) {
            habitacionConSalida = salidas.get("south");
        }
        if(direction.equals("west")) {
            habitacionConSalida = salidas.get("west");
        }
        if(direction.equals("southEast")) {
            habitacionConSalida = salidas.get("southEast");
        }
        if(direction.equals("northWest")) {
            habitacionConSalida = salidas.get("northWest");
        }
        return habitacionConSalida;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        String devuelve = "";
        if(northExit != null) {
            devuelve += "north ";
        }
        if(eastExit != null) {
            devuelve += "east ";
        }
        if(southExit != null) {
            devuelve += "south ";
        }
        if(westExit != null) {
            devuelve += "west ";
        }
        if(southEastExit != null) {
            devuelve += "southEast ";
        }
        if(nothWestExit != null) {
            devuelve += "northWest ";
        }
        return devuelve;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
}
