/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: Instruccion
Estado del código: Revisado, documentado y funcionando.
Fecha: 28/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------

*/

public abstract class Instruccion {

    // linea hace referencia a la línea que genera la instrucción (Solo depuración de errores)
    // No genera código
    private int linea ;

    Instruccion(int linea){
        this.linea = linea ;
    }

    public int getLinea(){
        return this.linea ;
    }

    public abstract Objeto generarCodigo() throws Exception ;
}
