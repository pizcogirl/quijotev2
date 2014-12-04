
/**
 * Bebedor de copas
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bebedor
{
    // Nombre del bebedor
    private String nombre;
    // Cantidad de alcohol en sangre
    private int alcoholEnSangre;
    // Limite de alcohol que puede tener en sangre
    private int limiteAlcohol;

    /**
     * Constructor para los bebedores. Introduce el nombre y cuanto alcohol aguanta.
     */
    public Bebedor(String nombre, int limiteAlcohol)
    {
        // Inicializa las variables, el alcohol en sangre siempre inicia en 0
        this.nombre = nombre;
        this.limiteAlcohol = limiteAlcohol;
        alcoholEnSangre = 0;
    }

    /**
     * Da una copa al bebedor, aumenta su alcohol en sangre
     */
    public void darCopa(Cubata nombreCopa)
    {
        // Comprobamos si puede aguantar la copa que le damos, si es asi la bebe, sino la rechaza
        if (haLlegadoAlLimite())
        {
            System.out.println("No quiero más copas");
        }
        else
        {
            // Al beber la copa, el nivel de alcohol en sangre se incrementa en la 
            //cuantia de la cantidad de alcohol de la bebida
            alcoholEnSangre = alcoholEnSangre + nombreCopa.getCantidadAlcohol();
        }
    }

    /**
     * Hace una pregunta al bebedor
     */
    public void hacerPreguntas(String pregunta)
    {
        if ((haLlegadoAlLimite()) || (pregunta.contains(nombre)))
        {
            // Si se ha pasado del limite o la pregunta contiene su nombre, 
            // responde con la pregunta a gritos
            pregunta = pregunta.toUpperCase();
            System.out.println(pregunta);
        }
        else
        {
            // Si no se ha pasado del limite, responde con si si el numero de caracteres
            // de la pregunta es par y no si es impar
            if (((pregunta.length())%2) == 0)
            {
                System.out.println("Si");
            }
            else
            {
                System.out.println("No");
            }
            
            // *********************************
            
            /*int numLetrasPregunta = 0;
            for (int i = 0; i < pregunta.length; i++)
            {
                if (Character.isAlphabetic(pregunta[i]))
                {
                    numLetrasPregunta++;
                }
            }
            
            if ((numLetrasPregunta%2) == 0)
            {
                System.out.println("Si");
            }
            else
            {
                System.out.println("No");
            }
            */
            // *********************************            
        }
    }

    /**
     * Comprueba si el bebedor se ha pasado del limite de alcohol en sangre que
     * tenga, devuelve un boolean true si se ha pasado, false si no.
     */
    private boolean haLlegadoAlLimite()
    {
        boolean bebidoDemasiado = false;
        if (limiteAlcohol < alcoholEnSangre)
        {
            bebidoDemasiado = true;
        }
        else
        {
            bebidoDemasiado = false;
        }
        return bebidoDemasiado;
    }
}
