/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: LlamadaMetodo
Estado del código: Revisado, documentado y funcionando.
Fecha: 28/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------
Clase para generar código, el árbol de código.

*/
public class LlamadaMetodo extends Instruccion {

    private Instruccion instruc1 ;
    private String metodo ;
    private Instruccion param[]  ;

    // Guardar una lista de parámetros en un array.
    public LlamadaMetodo(int linea, Instruccion instruc1, String metodo, Instruccion[] param) {
        super(linea); // La línea Solo la guardamos por motivos de depuración
        this.instruc1 = instruc1;
        this.metodo = metodo;
        this.param = param;
    }


    /**
     * Generates the three-address code (CTD) for the method call.
     * This method processes the `instruc1` attribute to generate its code,
     * iterates over the parameters to generate their code blocks, and finally
     * combines these pieces into the resulting code representation of the method call.
     *
     * @return An object representing the generated three-address code for the method call.
     * @throws Exception If an error occurs during code generation.
     */
    /* Se coge el atributo instruc1 y se generea su código de 3 direcciones (CTD) */
    @Override
    public Objeto generarCodigo() throws Exception {

        Objeto ParamObjeto [] = null;

        // el atributo instruc1 se había generado anteriormente en el CUP.
        // instruc1 es el objeto que ejecuta el método.
        // No se mete en el array por estética.
        Objeto o1 = instruc1.generarCodigo() ;

        if (param != null) {
            ParamObjeto = new Objeto[param.length];

            /* Me genera el bloque de código y va recorriendo cada parámetro guardando en un array. */
            for (int k = 0; k < param.length; k++) {
                // Obtener el código necesario para crear cada parámetro.
                ParamObjeto[k] = param[k].generarCodigo(); // Se genera código para los parámetros.
            }
        }

        // getLinea me viene del CUP cuando está construyendo el arbol.
        // el objeto que se creo en la linea tal, tiene un problema o tiene este otro.
        // REsultado para el primer bloque pasandole todos los objetos que se han ido calculando
        Objeto r = o1.generarCodigoMetodo(metodo, ParamObjeto, getLinea()); // Esto genera $a + $t
        return r;
    }
}
