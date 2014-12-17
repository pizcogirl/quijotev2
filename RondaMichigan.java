import java.util.ArrayList;
import java.util.Random;

/**
 * Simulamos una ronda de michigan (juego de dados)
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
    // Guarda la posicion del peor jugador de la ronda
    private int posicionPeorJugador;
    // Guarda el peor jugador de la ronda
    private Bebedor peorJugador;
    // Guarda en una coleccion las posiciones de los jugadores que obtienen un michigan en esta ronda
    private ArrayList<Integer> posicionMichigan;

    /**
     * Constructor for objects of class RondaMichigan
     */
    public RondaMichigan()
    {
        // Inicializamos las ArrayList vacias y las demas variables a 0 o valores
        // no posibles
        jugadores = new ArrayList<Bebedor>();
        peorTirada = 0;
        posicionPeorJugador = -1;
        peorJugador = null;
        posicionMichigan = new ArrayList<Integer>();
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
        // Inicializamos la peor tirada con un valor no posible, para luego ir comparando con la de cada jugador
        int peorTirada = 16;
        // Guarda la posicion del peor o los peores jugadores de la ronda
        ArrayList<Integer> listaPosicionPeorJugador = new ArrayList<Integer>();
        // Guarda el o los peores jugadores de la ronda
        ArrayList<Bebedor> listaPeorJugador = new ArrayList<Bebedor>();
        for ( Bebedor jugador : jugadores)
        {
            // Para cada jugador realizamos la tirada, y guardamos la peor junto con el peor jugador para facilitar la cuenta de puntos
            int puntuacion = hacerTirada(jugador.getNombre());
            // Si hay empate crearemos una lista, y despues jugaran una ronda extra entre ellos hasta que quede uno
            if (puntuacion == 15)
            {
                // Si el jugador saca un 1 y un 2 (Michigan, que la maquina evalua como 15 puntos) se guarda su posicion en una arraylist
                posicionMichigan.add(jugadores.indexOf(jugador));
            }
            if (puntuacion == peorTirada)
            {
                // Si hay mas de un perdedor, nos guardara todos en una arraylist y despues realizaremos los desempates
                peorTirada = puntuacion;
                listaPeorJugador.add(jugador);
                listaPosicionPeorJugador.add(jugadores.indexOf(jugador));
            }
            else if (puntuacion < peorTirada)
            {
                peorTirada = puntuacion;
                peorJugador = jugador;
                posicionPeorJugador = jugadores.indexOf(peorJugador);
                listaPeorJugador.clear();
                listaPosicionPeorJugador.clear();
            }
        }
        // En caso de empate, se juega una ronda de desempate entre los perdedores hasta que solo quede uno
        while (listaPeorJugador.size() > 1)
        {
            for ( Bebedor jugador : listaPeorJugador)
            {
                // Para cada jugador realizamos la tirada, y guardamos la peor junto con el peor jugador para facilitar la cuenta de puntos
                int puntuacion = hacerTirada(jugador.getNombre());
                if (puntuacion < peorTirada)
                {
                    peorTirada = puntuacion;
                    peorJugador = jugador;
                    posicionPeorJugador = listaPosicionPeorJugador.get(listaPeorJugador.indexOf(jugador));
                }
            }
        }
        // Imrpime un mensaje avisando quien ha sacado la peor puntuacion esta ronda
        System.out.println ("El jugador " + peorJugador.getNombre() + " ha sacado la peor tirada");
    }

    /**
     * Devuelve el numero de jugadores inscritos en la partida
     */
    public int getNumeroJugadores()
    {
        return jugadores.size();
    }

    /**
     * Devuelve la posicion en el indice del jugador perdedor de la ronda
     */
    public int getIndicePerdedor()
    {
        return posicionPeorJugador;
    }

    /**
     * Devuelve el jugador perdedor de la ronda
     */
    public Bebedor getPerdedor()
    {
        return peorJugador;
    }

    /**
     * Elimina el jugador con el numero indicado de la ronda
     */
    public void borrarJugador(int index)
    {
        jugadores.remove(index);
    }

    /**
     * Devuelve el jugador con el numero indicado de la ronda
     */
    public Bebedor getJugador(int index)
    {
        return jugadores.get(index);
    }

    /**
     * Devuelve el primer jugador de la lista
     */
    public Bebedor getJugador()
    {
        return jugadores.get(0);
    }

    /**
     * Devuelve la lista de posiciones de jugadores con michigan
     */
    public ArrayList<Integer> getPosicionMichigan()
    {
        return posicionMichigan;
    }

    /**
     * Borra la lista de posiciones de jugadores con michigan
     */
    public void borraMichigan()
    {
        posicionMichigan.clear();
    }

    /**
     * Metodo para realizar las tiradas de cada jugador
     */
    private int hacerTirada(String nombre)
    {
        // Creamos los dados para las tiradas
        int dado1 = 0;
        int dado2 = 0;
        // Creamos variables locales para almacenar los puntos y dos random para simular tiradas aleatorias
        int puntos = 0;
        // A uno de ellos le añadimos una seed distinta para conseguir una mejor aleatoriedad
        Random numRan1 = new Random(System.currentTimeMillis());
        Random numRan2 = new Random();
        dado1 = numRan1.nextInt(6) + 1;
        dado2 = numRan2.nextInt(6) + 1;
        // Con un if recogemos todas las posibles puntuaciones que podemos obtener
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
        // Imprime la puntuacion de cada jugador
        System.out.println("El jugador " + nombre + " lanza los dados y obtiene " + dado1 + " y " + dado2);
        return puntos;
    }
}
