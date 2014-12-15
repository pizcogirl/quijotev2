import java.util.ArrayList;
import java.util.Random;

/**
 * Simulamos una ronda de michigan (juego de dados)
 * Reglas de cada ronda introducidas:
 * - Si sale un doble, se envia un reto, el perdedor bebe una copa
 * - Si sale 1 + 2, es michigan, el autor es ganador de la ronda
 * - El jugador con menor puntuacion es el perdedor de la ronda
 * - Las valoraciones van de la siguiente manera: michigan gana a todo,
 *   despues van los dobles, por orden de valor, por ultimo las tiradas 
 *   simples excluido el 1 + 2
 * - Los jugadores solo disponen de una tirada (version simplificada)
 * Existen otras ocurrencias especiales, por ahora excluidas
 * 
 * @author (Julia Zuara) 
 * @version (a version number or a date)
 */
public class RondaMichigan
{
    // Jugadores que toman parte en esta ronda
    private ArrayList<Bebedor> jugadores;
    // Guarda la peor tirada de la ronda para saber quien es el perdedor
    private int peorTirada;
    // Guarda el peor jugador de la ronda
    private Bebedor peorJugador;

    /**
     * Constructor for objects of class RondaMichigan
     */
    public RondaMichigan()
    {
        // Inicializamos la ArrayList vacia y las demas variables a 0 o null
        jugadores = new ArrayList<Bebedor>();
        peorTirada = 0;
        peorJugador = null;
    }

    /**
     * Añadimos jugadores a la ronda de michigan
     */
    public void añadirJugador(Bebedor jugadorNuevo)
    {
        jugadores.add(jugadorNuevo);
    }

    /**
     * Tiramos los dados para cada jugador
     */
    public void jugarRonda()
    {
        for ( Bebedor jugador : jugadores)
        {
            // Para cada jugador realizamos la tirada, y guardamos la peor junto con el peor jugador para facilitar la cuenta de puntos
            int puntuacion = hacerTirada();
            int peorTirada = 0;
            if (puntuacion < peorTirada)
            {
                peorTirada = puntuacion;
                peorJugador = jugador;
            }
            // Imprime la puntuacion de cada jugador
            System.out.println("El jugador " + jugador.getNombre() + " ha sacado una puntuacion de " + puntuacion);
        }
        // Imrpime un mensaje avisando quien ha sacado la peor puntuacion esta ronda
        System.out.println ("El jugador " + peorJugador.getNombre() + " ha sacado la peor puntuacion");
    }

    /**
     * Metodo para realizar las tiradas de cada jugador
     */
    private int hacerTirada()
    {
        int puntos = 0;
        int dado1 = 0;
        int dado2 = 0;
        Random numRan1 = new Random();
        Random numRan2 = new Random();
        dado1 = numRan1.nextInt(7);
        dado2 = numRan2.nextInt(7);
        // COn un if recogemos todas las posibles puntuaciones que podemos obtener
        if (((dado1 == 1) && (dado2 == 2)) || ((dado1 == 2) && (dado2 == 1)))
        {
            // Un michigan es la maxima tirada, sacando un 1 y un 2
            puntos = 15;
        }
        else if ((dado1 == 6) && (dado2 == 6))
        {
            // Dos seises es la segunda mejor tirada, genera reto
            puntos = 14;
        }
        else if ((dado1 == 5) && (dado2 == 5))
        {
            // Dos 5, tercera mejor tirada, genera reto
            puntos = 13;
        }
        else if ((dado1 == 4) && (dado2 == 4))
        {
            // Dos 4, cuarta mejor tirada, genera reto
            puntos = 12;
        }
        else if ((dado1 == 3) && (dado2 == 3))
        {
            // Dos 3, quinta mejor tirada, genera reto
            puntos = 11;
        }
        else if ((dado1 == 2) && (dado2 == 2))
        {
            // Dos 2, sexta mejor tirada, genera reto
            puntos = 10;
        }
        else if ((dado1 == 1) && (dado2 == 1))
        {
            // Dos 1, septima mejor tirada, genera reto, ultimo doble
            puntos = 9;
        }
        else if (((dado1 == 5) && (dado2 == 6)) || ((dado1 == 6) && (dado2 == 5)))
        {
            // Las tiradas no dobles van en funcion de la suma de ambos dados, 11 es la mejor de las simples
            puntos = 8;
        }
        else if (((dado1 == 6) && (dado2 == 4)) || ((dado1 == 4) && (dado2 == 6)))
        {
            // La tirada resultante es un 10
            puntos = 7;
        }
        else if (((dado1 == 6) && (dado2 == 3)) || ((dado1 == 3) && (dado2 == 6)) || ((dado1 == 5) && (dado2 == 4)) || ((dado1 == 4) && (dado2 == 5)))
        {
            // La tirada resultante es un 9
            puntos = 6;
        }
        else if (((dado1 == 5) && (dado2 == 3)) || ((dado1 == 3) && (dado2 == 5)) || ((dado1 == 6) && (dado2 == 2)) || ((dado1 == 2) && (dado2 == 6)))
        {
            // La tirada resultante es un 8
            puntos = 5;
        }
        else if (((dado1 == 4) && (dado2 == 3)) || ((dado1 == 3) && (dado2 == 4)) || ((dado1 == 5) && (dado2 == 2)) || ((dado1 == 2) && (dado2 == 5)) 
        || ((dado1 == 6) && (dado2 == 1)) || ((dado1 == 1) && (dado2 == 6)))
        {
            // La tirada resultante es un 7
            puntos = 4;
        }
        else if (((dado1 == 4) && (dado2 == 2)) || ((dado1 == 2) && (dado2 == 4)) || ((dado1 == 5) && (dado2 == 1)) || ((dado1 == 1) && (dado2 == 5)))
        {
            // La tirada resultante es un 6
            puntos = 3;
        }
        else if (((dado1 == 4) && (dado2 == 1)) || ((dado1 == 1) && (dado2 == 4)) || ((dado1 == 3) && (dado2 == 2)) || ((dado1 == 2) && (dado2 == 3)))
        {
            // La tirada resultante es un 5
            puntos = 2;
        }
        else
        {
            // Solo queda la posibilidad del 4, con las tiradas 3 y 1 o 1 y 3, que es la peor tirada posible
            puntos = 1;
        }

        return puntos;
    }
}
