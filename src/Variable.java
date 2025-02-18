/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: Variable
Estado del código: Revisado, documentado y funcionando.
Fecha: 28/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------

*/
public class Variable extends Instancia {

    public Variable(String nombreObjeto, int numBloque, boolean mutable, Tipo tipo) {
        super(nombreObjeto, numBloque, mutable, tipo);
    }

    @Override
    public Objeto generarCodigoMetodo(String metodo, Objeto[] param, int linea) throws Exception {
        return getTipo().generarCodigoInstancia(this,metodo,param,linea);
    }

    /*
     * No hay generar codigo instancia poruqe las variables yo lo son en si
     * Busca el tipo y le pide que el tipo genere el código.
     * Para poder sumar 2 cosas, se necesitan tener esas cosas, y luego su código.
     * Por tanto, necesitamos generarCodigo()
     */
}
