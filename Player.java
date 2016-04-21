import java.util.Stack;
import java.util.ArrayList;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    // La habitacion actual donde esta el jugador
    private Room currentRoom;
    // La habitacion anterior donde estuvo el jugador
    private Room habitacionAnterior;
    // Apila todas las habitaciones en las que se ha estado el jugador
    private Stack<Room> habitacionesAnteriores;
    // Los objetos que lleva el jugador
    private ArrayList<Item> mochila;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(String direccion) 
    {
        if (currentRoom.getExit(direccion) != null) {
            
        } 
    }
}
