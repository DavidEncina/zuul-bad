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
    // Almacena la vida maxima que puede tener el jugador
    private final static int VIDAMAXIMA = 10;
    // Almacena la vida que tiene el personaje. Comienza en 10
    private int vidaActual;
    // Almacena si el jugador tiene aún vida o no
    private boolean sinVida;

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
        vidaActual = 10;
        boolean sinVida = false;
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
    
    /**
     * Metodo para que el jugador tire objetos en la habitacion
     */
    public void dejarObjeto(String descripcion)
    {
        Item objeto = buscarObjeto(descripcion);
        mochila.remove(objeto);
        currentRoom.addItem(objeto);
    }
    
    /**
     * Busca un objeto en la lista de objetos que lleva el jugador
     */
    public Item buscarObjeto(String descripcion)
    {
        Item objeto = null;
        boolean objetoEncontrado = false;
        for (int i = 0; i < mochila.size() && !objetoEncontrado; i++) {
            if (mochila.get(i).getDescripcion().equals(descripcion)) {
                objeto = mochila.get(i);
                objetoEncontrado = true;
            }
        }
        return objeto;
    }
    
    /**
     * Imprime los objetos que lleva el jugador
     */
    public void verMochila()
    {
        for (Item objeto : mochila) {
            System.out.println(objeto);
        }
    }
    
    /**
     * Aumenta la vida del jugador.
     * No se podra superar la vida maxima de este
     */
    public void sumarVida()
    {
        if (vidaActual == VIDAMAXIMA) {
            System.out.println("Ya tienes la vida al maximo");
        }
        else if (vidaActual + 5 >= VIDAMAXIMA) {
            vidaActual = 10;
        }
        else {
            vidaActual = vidaActual + 5;
        }
    }
    
    /**
     * Resta puntos de vida al jugador
     * Si llega a cero se pierde el juego
     */
    public void restarVida() 
    {        
        if (vidaActual - 5 > 0) {
            vidaActual = vidaActual - 5;
        }
        else {
            System.out.println("Te has quedado sin vida");
            System.out.println("----------------------  GAME OVER  -----------------------");
            sinVida = true;
        }
    }
    
    /**
     * Devielve si el jugador tiene aún vida o no
     */
    public boolean getSinVida() 
    {
        return sinVida;
    }
}
