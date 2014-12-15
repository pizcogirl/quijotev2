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
    // Almacena la ronda en la que nos encontramos
    private int numeroRonda;

    /**
     * Constructor para la partida de Michigan, crea la primera ronda
     * de la partida.
     */
    public Michigan()
    {
        // Inicializamos las arraylist y el numero de rondas a 0
        rondasPartida = new ArrayList<RondaMichigan>();
        puntuacion = new ArrayList<Integer>();
        numeroRonda = 0;
        // Comprobamos si la ronda esta vacia, de ser asi podemos inicializarlo
        RondaMichigan rondaInicial = new RondaMichigan();
        rondasPartida.add(rondaInicial);
    }
    
    /**
     * Añade jugadores a la partida de michigan. Los añade a la ronda con una puntuacion de 0.
     */
    public void añadirJugador(Bebedor jugadorNuevo)
    {
        // Almacenamos la ronda indicada en el indice
        RondaMichigan rondaTemporal = rondasPartida.get(numeroRonda);
        // En esa ronda añadimos el jugador nuevo, y a la lista de puntuaciones añadimos una que sera 0
        rondaTemporal.añadirJugador(jugadorNuevo);
        puntuacion.add(0);
        
    }
    
    /**
     * Realiza una ronda de michigan
     */
    public void jugarRonda()
    {
        // Almacenamos la ronda en la que nos encontramos
        RondaMichigan rondaTemporal = rondasPartida.get(numeroRonda);
        // Realizamos la ronda de tiradas y obtenemos la posicion del jugador perdedor
        rondaTemporal.jugarRonda();
        int indicePerdedor = rondaTemporal.getIndicePerdedor();
        // Sumamos un punto a la puntuacion del perdedor. Para ello guardamos la puntuacion anterior + 1,
        // la borramos e insertamos la nueva puntuacion en la lista
        int puntos = puntuacion.get(indicePerdedor) + 1;
        puntuacion.remove(indicePerdedor);
        puntuacion.add(indicePerdedor, puntos);
        // Ahora mostraremos las puntuaciones por pantalla
        for (Integer puntuaciones : puntuacion)
        {
            System.out.println(puntuaciones + "");
        }
    }
        
}
