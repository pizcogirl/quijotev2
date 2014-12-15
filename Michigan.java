import java.util.ArrayList;

/**
 * Esta clase representa una partida completa de michigan.
 * El conteo de las puntuaciones se llevara aqui, asi como las
 * penalizaciones por cada ronda que obligan a beber
 * 
 * @author (Julia Zuara) 
 * @version (a version number or a date)
 */
public class Michigan
{
    // Almacenamos la lista de rondas que componen la partida
    private ArrayList<RondaMichigan> rondasPartida;
    // Almacenamos la puntuacion de cada jugador en otra lista
    private ArrayList<Integer> puntuacion;
    // 

    /**
     * Constructor para la partida de Michigan, se inicia introduciendo una ronda
     * de michigan sin jugadores apuntados aun
     */
    public Michigan(RondaMichigan rondaInicial)
    {
        // Inicializamos las arraylist
        rondasPartida = new ArrayList<RondaMichigan>();
        puntuacion = new ArrayList<Integer>();
        // Comprobamos si la ronda esta vacia, de ser asi podemos inicializarlo
        if (rondaInicial.getJugadores() == 0)
        {
            rondasPartida.add(rondaInicial);
            puntuacion = null;
        }
        else
        {
            System.out.println("Esa ronda no es valida para iniciar una partida de michigan");
        }
    }
    
    /**
     * Añade jugadores a la partida de michigan. Los añade a la ronda con la mayor puntuacion
     * que haya en ese momento
     */
    public void añadirJugador(Bebedor jugadorNuevo)
    {
        
    }
}
