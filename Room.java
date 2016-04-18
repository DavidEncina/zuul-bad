import java.util.HashMap;
import java.util.ArrayList;

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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> salidas;
    private ArrayList<Item> objetos;

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
        objetos = new ArrayList<Item>();
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
        habitacionConSalida = salidas.get(direction);
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
        for (String key : salidas.keySet()){
            devuelve += key + " ";
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
        String descripcion = "Estas " + description + "\nSalidas: " + getExitString();
        if (objetos.size() > 0) {
            for(int i = 0; i < objetos.size(); i++)
            {
                descripcion += objetos.get(i);
            }            
        }
        else {
            descripcion += "\nNo hay ningun objeto en esta habitacion";
        }
        return descripcion;
    }
    
    /**
     * Añade un objeto a una localización
     */
    public void addItem(Item objeto)
    {
        objetos.add(objeto);
    }
}
