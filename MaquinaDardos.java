import java.util.ArrayList;

/**
 * Simula una maquina de dardos con la que pueden interactuar los borrachos para jugar entre ellos
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MaquinaDardos
{
    // guarda los jugadores de la partida en curso y sus puntuaciones
    private ArrayList<Jugador> partida;
    // guarda el numero de ronda en el que se encuentran
    private int ronda;

    /**
     * Constructor para las partidas en la maquina de dardos
     */
    public MaquinaDardos()
    {
        // inicializamos la partida con cero jugadores y en la ronda 0
        partida = new ArrayList<Jugador>();
        ronda = 0;
    }

    /**
     * Introduce nuevo jugadores a la partida
     */
    public void nuevoJugador(String nombre)
    {
        //Si el juego aun no ha empezado, admite nuevos jugadores
        if (ronda == 0)
        {
            Jugador jugadorNuevo = new Jugador (nombre);
            partida.add(jugadorNuevo);
        }
        else
        {
            System.out.println ("No se pueden unir jugadores a mitad de la partida");
        }
    }
}
