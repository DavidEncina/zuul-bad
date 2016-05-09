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
    private boolean puertaAbierta;
    private HashMap<String, Boolean> puertas;

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
        puertas = new HashMap<String, Boolean>();
    }

    /**
     * Define an exit from this room.
     * Devuelve si la puerta a otra habitacion esta abierta o no
     */
    public void setExit(String direction, Room neighbor, boolean puerta)
    {
        salidas.put(direction, neighbor);
        puertas.put(direction, puerta);
    }
    
    /**
     * Devuelve si la puerta a otra habitacion esta abierta o no
     */
    public boolean getPuertaAbierta(String direction)
    {        
        return puertas.get(direction);
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
    
    /**
     * Busca un objeto en la lista de objetos que hay en la habitacion
     */
    public Item buscarObjeto(String descripcion)
    {
        Item objeto = null;
        boolean objetoEncontrado = false;
        for (int i = 0; i < objetos.size() && !objetoEncontrado; i++) {
            if (objetos.get(i).getDescripcion().equals(descripcion)) {
                objeto = objetos.get(i);
                objetoEncontrado = true;
            }
        }
        return objeto;
    }
    
    /**
     * Elimina un objeto de la habitacion
     */
    public void borrarObjeto(Item objeto)
    {
        boolean objetoEncontrado = false;
        for (int i = 0; i < objetos.size() && !objetoEncontrado; i++) {
            if (objetos.get(i).getDescripcion().equals(objeto.getDescripcion())) {
                objetos.remove(objetos.get(i));
                objetoEncontrado = true;
            }
        }
    }
}
