
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // Almacena la descripcion del objeto
    private String descripcion;
    // Almacena el peso del objeto
    private float peso;

    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcion, float peso)
    {
        this.descripcion = descripcion;
        this.peso = peso;
    }

    /**
     * Devuelve la descripcion del objeto
     */
    public String getDescripcion()
    {
        return descripcion;
    }
    
    /**
     * Devuelve el peso del objeto
     */
    public float getPeso()
    {
        return peso;
    }
    
    /**
     * Devuelve el peso del objeto
     */
    public String toString()
    {
        return "\nObjeto: " + descripcion + "  ---  Peso: " + peso;
    }
}
