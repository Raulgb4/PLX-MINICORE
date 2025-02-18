/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: Instancia
Estado del código: Revisado, documentado y funcionando.
Fecha: 28/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------

*/
public abstract class Instancia extends Objeto {

    /**
     * Represents the type associated with this instance. This is a final variable that defines
     * the specific type characteristics for the current instance of the class that extends
     * the functionality of a 'Tipo'. The 'tipo' provides a structure for determining
     * predefined data types and methods for generating instance-specific code.
     */
    private final Tipo tipo ;

    /**
     * Constructs a new Instancia with the specified attributes.
     *
     * @param nombreObjeto the name of the block, serving as an identifier for this instance
     * @param numBloque the block number representing the context or scope of this instance
     * @param mutable a boolean indicating whether this instance is mutable
     * @param tipo the Tipo associated with this instance
     */
    public Instancia(String nombreObjeto, int numBloque, boolean mutable, Tipo tipo) {
        super(nombreObjeto, numBloque, mutable);
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }
}
