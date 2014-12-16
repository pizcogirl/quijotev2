import java.util.ArrayList;

/**
 * Esta clase representa una partida completa de michigan.
 * Reglas de cada ronda introducidas:
 * - Si sale 1 + 2, es michigan, el autor es ganador de la ronda(SIMPLIFICADO)
 * - El jugador con menor puntuacion es el perdedor de la ronda. Los
 *   empates se dirimen con rondas extra entre los perdedores(IMPLEMENTADO)
 * - Las valoraciones van de la siguiente manera: michigan gana a todo,
 *   despues van los dobles, por orden de valor, por ultimo las tiradas 
 *   simples excluido el 1 + 2 (IMPLEMENTADO)
 * - Los jugadores solo disponen de una tirada, deberian ser 3 (SIMPLIFICADO)
 * - Si sale un doble, se envia un reto, el perdedor bebe una copa(PENDIENTE)
 * - Los perdedores deberian beber por cada punto (PENDIENTE)
 * - Sacar un 9 hace beber al jugador siguiente (PENDIENTE)
 * - Sacar un 7 hace beber al jugador anterior (PENDIENTE)
 * - Sacar un 8 hace beber a uno de los jugadores (PENDIENTE)
 * - El ganador de cada ronda manda beber a otro jugador (PENDIENTE)
 * Condiciones de victoria/derrota/otras:
 * - Los jugadores empiezan con 0 puntos (IMPLEMENTADO)
 * - El jugador que pierde la ronda suma 1 punto (IMPLEMENTADO)
 * - Los jugadores abandonan la partida al tener mas de 25 puntos (IMPLEMENTADO)
 * - El ultimo jugador que queda es el ganador (IMPLEMENTADO)
 * - Si sale michigan, todos los jugadores menos el que saca el michigan suman 
 *   un punto (PENDIENTE)
 * - Las rondas son relevantes, se almacenan y se pueden consultar (DESCARTADO ESTA VERSION)
 * - 
 * 
 * 
 * @author (Julia Zuara) 
 * @version (a version number or a date)
 */
public class Michigan
{
    // Atributo para realizar las rondas
    private RondaMichigan rondasPartida;
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
        // Inicializamos la arraylist, la ronda de michigan y el numero de rondas a 0
        rondasPartida = new RondaMichigan();
        puntuacion = new ArrayList<Integer>();
        numeroRonda = 0;
    }

    /**
     * Añade jugadores a la partida de michigan. Los añade a la ronda con una puntuacion de 0.
     * Solo se aceptan nuevos jugadores en la primera ronda
     */
    public void añadirJugador(Bebedor jugadorNuevo)
    {
        // Comprueba si la ronda es la primera, en cuyo caso admite nuevos jugadores
        if (numeroRonda == 0)
        {
            // En esa ronda añadimos el jugador nuevo, y a la lista de puntuaciones añadimos una que sera 0
            rondasPartida.añadirJugador(jugadorNuevo);
            puntuacion.add(0);
        }
        // Si la partida ya esta empezada, no acepta nuevos jugadores
        else
        {
            System.out.println("No se admiten jugadores a mitad de la partida");
        }

    }

    /**
     * Realiza una ronda de michigan
     */
    public void jugarRonda()
    {
        // Realizamos la ronda de tiradas y obtenemos la posicion del jugador perdedor
        rondasPartida.jugarRonda();
        int indicePerdedor = rondasPartida.getIndicePerdedor();
        // Sumamos un punto a la puntuacion del perdedor. Para ello guardamos la puntuacion anterior + 1,
        // la borramos e insertamos la nueva puntuacion en la lista
        int puntos = puntuacion.get(indicePerdedor) + 1;
        puntuacion.remove(indicePerdedor);
        puntuacion.add(indicePerdedor, puntos);

        // Ahora mostraremos las puntuaciones por pantalla y comprobamos si algun jugador abandona la partida
        System.out.println("En la ronda " + numeroRonda + " las puntuaciones han sido:");
        for (Integer puntuaciones : puntuacion)
        {
            // Imprimimos todas las puntuaciones por pantalla
            System.out.println(puntuaciones + "");
            // Si alguna puntuacion llega a más de 25, se va de la partida
            if (puntuaciones > 25)
            {
                int posicionSeVa = puntuacion.indexOf(puntuaciones);
                eliminarJugador(posicionSeVa);
            }
        }
        // Si solo queda un jugador en la partida, la partida termina
        if (rondasPartida.getNumeroJugadores() == 1)
        {
            finPartida();
        }
        else
        {
            // Sumamos uno al numero de rondas
            numeroRonda = numeroRonda + 1;
        }
    }

    /**
     * Elimina a un jugador de la partida
     */
    private void eliminarJugador(int index)
    {
        // Borra el jugador que se ha ido y su puntuacion, imprime un mensaje por pantalla
        Bebedor perdedor = rondasPartida.getJugador(index);
        rondasPartida.borrarJugador(index);
        puntuacion.remove(index);
        System.out.println("El jugador " + perdedor.getNombre()  + " se ha salido de la partida");
    }

    /**
     * Finaliza la partida de michigan
     */
    private void finPartida()
    {
        // Almacena el ultimo jugador que queda en la partida, y anuncia que es el ganador
        Bebedor ganador = rondasPartida.getJugador();
        System.out.println("El ganador de esta partida ha sido " + ganador.getNombre() + " tras " + numeroRonda + " rondas");
        rondasPartida = null;
        puntuacion.clear();
        numeroRonda = 0;
    }

}
