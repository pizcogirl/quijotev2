
/**
 * Simula los datos de cada jugador de la partida
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Jugador
{
    // Nombre de cada jugador
    private String jugador;
    // Guarda la puntuación del jugador
    private int puntuacion;

    /**
     * Constructor for objects of class Jugador
     */
    public Jugador(String nombre)
    {
        // inicializamos con un valor de 0 puntos y el nombre del bebedor
        puntuacion = 0;
        jugador = nombre;
    }
    
    /**
     * Suma la puntuación de esta ronda al jugador
     */
    public void sumaPuntuacion(int puntos)
    {
        puntuacion = puntuacion + puntos;
    }
    
    /**
     * Devuelve los puntos acumulados hasta ese momento por un jugador
     */
    public int getPuntuacion()
    {
        return puntuacion;
    }
    
}