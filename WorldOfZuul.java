
/**
 * Write a description of class WorldOfZuul here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldOfZuul
{
    // Almacena el juego que se crea
    private static Game juego;

    /**
     * Metodo para iniciar el juego
     */
    public static void main (String [ ] args)
    {
        juego = new Game();
        juego.play();
    }
}
