/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: Objeto
Estado del código: Revisado, documentado y funcionando.
Fecha: 28/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

Objetivo del código: El término "Objeto" hace referencia a todos los elementos
del lenguaje, es decir, esta clase va a ser la tabla de símbolos del lenguaje.
Se debe guardar información como el nombreObjeto de variable, el tipo de variable, el tipo
del numBloque en el que está, funciones, constantes, nuevos tipos...

Esta clase debe responder a estas peticiones:
Ej 1: Dame toda la información sobre el tipo entero.
Ej 2: Dame toda la información sobre la variable a.

¿Por qué la case Objeto es comparable?
--------------------------------------

Para poder comparar si dos objetos son el mismo.
La tabla de símbolos va a ser un diccionario, las claves del diccionario deben ser comparables.
Por ello esta clase debe ser comparable -> public abstract class Objeto implements Comparable<Objeto>

¿Por qué la case Objeto es abstracta?
--------------------------------------

En la tabla de símbolos voy a derivar una serie de subclases abstractas que va a representar los objetos de mi lenguaje.
Una clase abstracta es una clase a medio implementar.
Por ello esta clase debe ser abstract -> public abstract class Objeto implements Comparable<Objeto>

Imaginemos el siguiente programa:

TipoInt Funcion      Instancia
 /     /              /
int suma (int a, int b) {
	return a+b ;
}
*/
public abstract class Objeto implements Comparable<Objeto> {

    private String nombreObjeto ; // Nombre del objeto
    private int numBloque ; // Número de bloque en el que está el objeto

    /**
     * Si lo que representa dicho objeto (es variable) puede cambiar o no
     * Lo único que puede ser mutable son las variables.
     * Las constantes no son mutables.
     */
    private boolean mutable ; // mutable -> True -> Variable y mutable -> False -> Constante

    private static int numObjeto = 0 ; // Número de objeto
    private static int numEtq = 0 ;    // Número de etiqueta

    /**
     * Genera nombres diferentes para objetos como nombres para variables,
     * tipos, constantes literales...
     *
     * @return $t0, $t1, $t2....
     */
    public static String newNombreObjeto() {
        String n = "$t" + Integer.toString(numObjeto);
        numObjeto++ ;
        return n ;
    }

    /**
     * Genera nombres diferentes para etiquetas
     *
     * @return L1, L2, L3....
     */
    public static String newEtiqueta() {
        String etq = "L" + numEtq;
        numEtq++ ;
        return etq ;
    }

    /**
     * Constructs a new Objeto instance with specified attributes.
     *
     * @param nombreObjeto  the name of the object, representing its identifier
     * @param numBloque  the block number indicating the scope or context of the object
     * @param mutable a boolean indicating whether the object can be mutated or not
     */
    public Objeto(String nombreObjeto, int numBloque, boolean mutable) {
        this.nombreObjeto = nombreObjeto;
        this.numBloque = numBloque;
        this.mutable = mutable;
    }

    /*
     * public Objeto(String nombreBloque, int numBloque, boolean mutable, Tipo tipo)
     * {
     *
     * }
     */

    /**
     * GETTERS Y SETTERS
     */
    public String getNombreObjeto() {
        return nombreObjeto;
    }
    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }
    public int getNumBloque() {
        return numBloque;
    }
    public boolean isMutable() {
        return mutable;
    }

    /**
     * Constructs a unique identifier for the object by combining its name and block number.
     * The identifier is formatted as {nombreObjeto}${numBloque}, which uniquely represents the object
     * within its context or scope.
     * Cada objeto de mi programa tiene un nombreObjeto, por ejemplo "int$1" para un entero en el numBloque 1.
     * Se pone el $ para no interferir con el programa, por ejemplo un "int". El $ se usa
     * para definir cosas internas de mi código.
     *
     * Ejemplo de la variable a en 2 bloques diferentes.
     *
     * int a = 7 ;
     * {
     *     int a = 1 ;
     * }
     *
     *
     * @return A string representing the unique identifier of the object. Ej: int$1 , a$0, a$1
     */
    public String getIDC() {
        return nombreObjeto + "$" + Integer.toString(numBloque);
    }

    /**
     * Generates a code representation for a method with the specified name and parameters.
     * This abstract method should be implemented to produce the appropriate code structure
     * based on the provided method name and an array of Objeto parameters.
     *
     * Llamar a un objeto con los parámetros que definen la operación que se quiere realizar.
     * Los objetos por el hecho de ser objetos deben implementar este metodo.
     *
     * Ejemplo: a un objeto número quiero decirle que realice la operación suma.
     * El CTD escribe por pantalla el metodo sumar.
     *
     * @param metodo the name of the method for which code is to be generated
     * @param param an array of Objeto instances representing the parameters of the method
     * @return an Objeto representing the generated code structure for the specified method
     * @throws Exception if an error occurs during the code generation process
     */
    public abstract Objeto generarCodigoMetodo(String metodo, Objeto [] param, int linea) throws Exception ;

    /**
     * Compares this object with the specified object for order. Returns a negative integer, zero,
     * or a positive integer as this object is less than, equal to, or greater than the specified object.
     * The comparison is primarily based on the 'numBloque' field, followed by the 'nombreObjeto' field if
     * the 'numBloque' fields are equal.
     *
     * @param o the object to be compared
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
     *         or greater than the specified object
     * @throws NullPointerException if the specified object is null
     */
    @Override
    public int compareTo(Objeto o) {
        if(o == null) {
            throw new NullPointerException("ERROR (Objeto): El objeto a comparar no puede ser nulo");
        }
        if(this.numBloque == o.numBloque) {
            return this.getNombreObjeto().compareTo(o.getNombreObjeto());
        } else {
            return this.numBloque - o.numBloque;
        }
    }

    /**
     * Compares this object to the specified object for equality.
     * Returns true if and only if the specified object is
     * not null, is an instance of the same class, and all fields
     * 'numBloque', 'nombreObjeto', and 'mutable' are equal.
     *
     * Este código lo he realizado yo, habría que revisarlo
     *
     * @param obj the object to be compared for equality with this instance
     * @return true if the specified object is equal to this instance; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        boolean eq = (obj instanceof Objeto);
        if(eq) {
            Objeto o = (Objeto) obj;

            eq = this.getNombreObjeto().equals(o.nombreObjeto) && this.numBloque == o.numBloque;
        }
        return eq;
    }
}
