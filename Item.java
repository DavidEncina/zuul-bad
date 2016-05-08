
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
    // Almacena si es posible coger un objeto
    private boolean seCoge;
    // Almacena si el objeto esta en buen estado o no
    private boolean buenEstado;
    // Almacena si el objeto es comestible o no
    private boolean comestible;

    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcion, float peso, boolean seCoge, boolean buenEstado, boolean comestible)
    {
        this.descripcion = descripcion;
        this.peso = peso;
        this.seCoge = seCoge;
        this.buenEstado = buenEstado;
        this.comestible = comestible;
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
     * Devuelve si el objeto se puede coger
     */
    public boolean getSeCoge()
    {
        return seCoge;
    }
    
    /**
     * Devuelve si el objeto se puede comer
     */
    public boolean getComestible()
    {
        return comestible;
    }
    
    /**
     * Devuelve el estado del objeto
     */
    public boolean getEstado()
    {
        return buenEstado;
    }
    
    /**
     * Devuelve el peso del objeto
     */
    public String toString()
    {
        return "\nObjeto: " + descripcion + "  ---  Peso: " + peso;
    }
}
