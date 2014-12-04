
/**
 * Representa a un cubata
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cubata
{
    // Nombre de la bebida
    private String nombre;
    // Cantidad de alcohol de la bebida
    private int cantidadAlcohol;
    
    /**
     * Constructor de cubatas. Introduce el nombre y la cantidad de alcohol
     */
    public Cubata(String nombre, int cantidadAlcohol)
    {
        // Inicializamos las variables con los parametros
        this.nombre = nombre;
        this.cantidadAlcohol = cantidadAlcohol;
    }

    /**
     * Devuelve la cantidad de alcohol de la bebida
     */
    public int getCantidadAlcohol()
    {
        return cantidadAlcohol;
    }
}
