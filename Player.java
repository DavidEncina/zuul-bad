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
    // Apila todas las habitaciones en las que se ha estado el jugador
    private Stack<Room> habitacionesAnteriores;
    // Los objetos que lleva el jugador
    private ArrayList<Item> mochila;
    // Almacena el peso maximo que puede llevar el jugador
    private float pesoMaximo;
    // Almacena el pèso que lleva en cada momento
    private float pesoActual;

    /**
     * Constructor for objects of class Player
     */
    public Player(float pesoMaximo)
    {
        currentRoom = null;
        habitacionesAnteriores = new Stack<Room>();
        this.pesoMaximo = pesoMaximo;
        mochila = new ArrayList<>();
        pesoActual = 0;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
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
            habitacionesAnteriores.push(currentRoom);            
            currentRoom = nextRoom;
            printLocationInfo();
            System.out.println();
        }

    }

    /** 
     * Ir a una habitacion anterior
     */
    public void goHabitacionAnterior()
    {        
        if (!habitacionesAnteriores.empty()) {            
            currentRoom = habitacionesAnteriores.pop();
        }
        else {
            System.out.println("No puedes volver a ningun sitio!");
            System.out.println("==============================================================================================\n");

        }
    }

    /**
     * Metodo interno para imprimir las localizaciones
     */
    public void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Coloca al jugador en la primera habitación
     */
    public void setRoom(Room room)
    {
        if (currentRoom != null) {
            habitacionesAnteriores.push(currentRoom);            
        }
        currentRoom = room;
    }
    
    /**
     * Metodo para que el jugador coja y almacene objetos
     */
    public void cogerObjeto(String descripcion)
    {
        Item objeto = currentRoom.buscarObjeto(descripcion);
        if (objeto != null && pesoActual + objeto.getPeso() < pesoMaximo && objeto.getSeCoge()) {
            mochila.add(objeto);
            pesoActual += objeto.getPeso();
            System.out.println("Has cogido el objeto " + descripcion);            
            currentRoom.borrarObjeto(objeto);
            printLocationInfo();
        }
        else if (!objeto.getSeCoge()) {
            System.out.println("Este objeto no se puede coger");
        }
        else if (pesoActual + objeto.getPeso() >= pesoMaximo) {
            System.out.println("No puedes coger el objeto " + descripcion + " porque pesa demasiado");
        }        
        else {
            System.out.println("No hay ningun objeto " + descripcion + " en la habitacion");
        }
    }
}
