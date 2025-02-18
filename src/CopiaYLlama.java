/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: Copia y Llama
Estado del código: Revisado, documentado y funcionando.
Fecha: 28/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------

La funcionalidad es parecida al LlamadaMetodo, esta clase se utiliza para
++i, ojo no es el i++

*/
public class CopiaYLlama extends Instruccion {

    private Instruccion exp;
    private String metodo;
    private Instruccion[] params;

    public CopiaYLlama(int linea, Instruccion exp, String metodo, Instruccion[] params) {
        super(linea);
        this.exp = exp;
        this.metodo = metodo;
        this.params = params;
    }

    @Override
    public Objeto generarCodigo() throws Exception {
        Objeto objParams[] = null;
        Objeto copia;

        // Se crea una nueva variable
        Variable obj = (Variable) exp.generarCodigo();

        // Se copia la variable en copia
        copia = new Variable(Objeto.newNombreObjeto(), obj.getNumBloque(), obj.isMutable(), obj.getTipo());
        copia.generarCodigoMetodo(Metodos.CREAR_VARIABLE, new Objeto[]{obj}, getLinea());

        if(params != null) {
            objParams = new Objeto[params.length];
            for(int i = 0; i < params.length; i++) {
                objParams[i] = params[i].generarCodigo();
            }
        }

        // Llamada al método pasándole los parámetros
        obj.generarCodigoMetodo(metodo, objParams, getLinea());

        // Se devuelve el valor anterior de la variable
        return copia;
    }
}