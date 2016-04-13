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
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor)
    {
        salidas.put(direction, neighbor);
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
        if(salidas.get("north") != null) {
            devuelve += "north ";
        }
        if(salidas.get("east") != null) {
            devuelve += "east ";
        }
        if(salidas.get("south") != null) {
            devuelve += "south ";
        }
        if(salidas.get("west") != null) {
            devuelve += "west ";
        }
        if(salidas.get("southEast") != null) {
            devuelve += "southEast ";
        }
        if(salidas.get("northWest") != null) {
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

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        return "Estas " + description + "\nSalidas: " + getExitString();
    }
}
