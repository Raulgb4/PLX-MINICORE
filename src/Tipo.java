import java.text.ParseException;

/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: Tipo
Estado del código: Revisado, documentado y funcionando.
Fecha: 28/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------
Objetivo del código: define una estructura básica
para manejar tipos, proporcionando un conjunto de tipos predefinidos y un
mecanismo para que las subclases generen código basado en instancias y métodos.

*/
public abstract class Tipo extends Objeto {

    /**
     * The TiposPredefinidos class provides a set of predefined constants representing
     * specific data types. These constants are used to identify the corresponding
     * data type within a given context or application.
     */
    public static final class TiposPredefinidos { // Funciona como un enumerado. Es una forma de conseguir un nombre para un tipo.
        public static final String ENTERO    = "$int";
        public static final String BOOLEANO  = "$boolean";
        public static final String CARACTER  = "$char";
        public static final String REAL      = "$float";
    }

    /**
     * Constructs a new Tipo instance with specified attributes, indicating the
     * identifier, scope, and mutability status.
     *
     * @param nombreObjeto the name of the block, serving as an identifier for this type
     * @param numBloque the block number representing the context or scope of this type
     * @param mutable a boolean indicating whether this type instance is mutable
     */
    public Tipo(String nombreObjeto, int numBloque, boolean mutable) {
        super(nombreObjeto, numBloque, mutable);
    }

    /**
     * Generates a code representation for a particular instance using the specified method name and parameters.
     * This abstract method should be implemented to produce the appropriate code structure based on the provided
     * instance, method name, and an array of parameters.
     *
     * Es el caso en el que le pedimos a la clase tipo que genere un código de la instancia del tipo
     *
     * @param instancia the instance for which to generate code
     * @param metodo the name of the method to be invoked on the instance
     * @param param an array of Objeto instances that serve as parameters for the method call
     * @return an Objeto representing the result of the code generation based on the given instance, method, and parameters
     */
    public abstract Objeto generarCodigoInstancia(Instancia instancia, String metodo, Objeto [] param, int linea) throws Exception;

}
