import java.util.ArrayList;
import java.util.Random;

/**
 * Simula una maquina de dardos con la que pueden interactuar los borrachos para jugar entre ellos
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MaquinaDardos
{
    // guarda los jugadores de la partida en curso y sus puntuaciones
    private ArrayList<Bebedor> partida;
    // guarda el numero de ronda en el que se encuentran
    private int ronda;

    /**
     * Constructor para las partidas en la maquina de dardos
     */
    public MaquinaDardos()
    {
        // inicializamos la partida con cero jugadores y en la ronda 0
        partida = new ArrayList<Bebedor>();
        ronda = 0;
    }

    /**
     * Introduce nuevos jugadores a la partida. Si la partida esta iniciada no se pueden añadir jugadores
     */
    public void nuevoJugador(Bebedor jugadorNuevo)
    {
        // Si el juego aun no ha empezado, admite nuevos jugadores
        if (ronda == 0)
        {
            
            partida.add(jugadorNuevo);
        }
        // Si no imprime un mensaje de error
        else
        {
            System.out.println ("No se pueden unir jugadores a mitad de la partida");
        }
    }

    /**
     * Simula una nueva ronda de dardos. Las partidas tienen 7 rondas.
     */
    public void rondaDardos()
    {
        // Primero comprobamos que existan jugadores activos en la partida, sino avisa
        if (partida.size() > 0)
        {
            // Comprueba si aun no se han jugado las 7 rondas
            if (ronda < 7)
            {
                // Suma uno al contador de rondas
                ronda = ronda + 1;
                // Para cada jugador tira los dados y muestra por pantalla su puntuacion
                for (Bebedor lanzadorDardos : partida)
                {
                    int alcohol = lanzadorDardos.getAlcoholEnSangre();
                    int limite = lanzadorDardos.getLimite();
                    int puntosRonda = calculaPuntuacion (alcohol, limite);
                    lanzadorDardos.sumaPuntuacion(puntosRonda);
                    System.out.println("La puntuacion del jugador " + lanzadorDardos.getNombre() + " es " + puntosRonda); 
                }
            }
            // Si ya se han jugado 7 rondas salta al final de la partida
            else
            {
                finPartida();
            }
        }
        else
        {
            System.out.println("Introduzca al menos un jugador");
        }
    }

    /**
     * Finaliza la partida de dardos y devuelve los puntos y el nombre del ganador
     */
    public void finPartida()
    {
        // Almacenaremos el ganador de la partida y los puntos obtenidos en variables locales
        String ganador = null;
        int maximoPuntos = 0;
        // Para cada jugador calculamos sus puntos, y se imprimen por pantalla junto con su nombre
        for (Bebedor jugador : partida)
        {
            int puntosJugador = jugador.getPuntuacion();
            System.out.println("El jugador " + jugador.getNombre() + " ha alcanzado " + puntosJugador + " puntos");
            // Siempre que la puntuacion del ultimo jugador mostrado supere a la puntuación maxima guardada
            // de los jugadores anteriores de la misma partida, se guarda la puntuación y el nombre del jugador
            if (puntosJugador > maximoPuntos)
            {
                maximoPuntos = puntosJugador;
                ganador = jugador.getJugador();
            }
            // Si la puntuación del jugador es igual al maximo que tengamos guardado, se guarda su nombre
            // tambien al ser tambien ganador
            else if (puntosJugador == maximoPuntos)
            {
                ganador = ganador + " y " + jugador.getNombre();
            }
        }
        // Una vez contabilizadas, mostramos por pantalla el o los ganadores, y la puntuacion maxima obtenida
        System.out.println ("El ganador de la partida ha sido " + ganador + 
        " con un total de " + maximoPuntos + " tras un total de " + ronda + " rondas");
        // Reinicia el numero de rondas y borra todos los participantes
        ronda = 0;
        partida.clear();
    }

    /**
     * Muestra por pantalla las puntuaciones de todos los jugadores hasta ahora
     */
    public void mostrarPuntuaciones()
    { 
        for (Bebedor jugador : partida)
        {
            System.out.println("El jugador " + jugador.getNombre() + " ha alcanzado " 
            + jugador.getPuntuacion() + " puntos tras un total de " + ronda + " rondas");
        }
    }

    /**
     * Calculo de los puntos del jugador en funcion del alcohol en sangre
     */
    private int calculaPuntuacion(int alcohol, int limite)
    {
        int puntos = 0;
        Random ranNum = new Random();
        // La puntuacion ira de 0 a 10 segun el alcohol en sangre
        if (alcohol == 0)
        {
            // Si no ha bebido, calcula una puntuacion entre 9 y 10
            puntos = ranNum.nextInt(2) + 9;
        }
        else if (alcohol < (limite/4))
        {
            // Si ha bebido menos de 1/4 de su limite, calcula una
            // puntuacion entre 7 y 8
            puntos = ranNum.nextInt(2) + 7;
        }
        else if (alcohol < (limite/2))
        {
            // Si ha bebido menos de 1/2 de su limite, calcula una
            // puntuacion entre 5 y 6
            puntos = ranNum.nextInt(2) + 5;
        }
        else if ((alcohol > (limite/2)) && (alcohol < limite))
        {
            // Si ha bebido mas de 1/2 pero menos de su limite,
            // calcula una puntuacion entre 2 y 4
            puntos = ranNum.nextInt(3) + 2;
        }
        else
        {
            // Si ha llegado o se ha pasado del limite,
            // calcula una puntuacion entre 0 y 1
            puntos = ranNum.nextInt(2);
        }
        return puntos;
    }
}
