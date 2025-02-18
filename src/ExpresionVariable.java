/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: ExpresionVariable
Estado del código: Revisado, documentado y funcionando.
Fecha: 28/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------

*/
public class ExpresionVariable extends Instruccion {

    private Variable v;

    public ExpresionVariable(int linea, Variable v) {
        super(linea);
        this.v = v;
    }

    @Override
    public Objeto generarCodigo() throws Exception {
        return v ;
    }
}
