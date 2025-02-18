/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: ListaVariable
Estado del código: Revisado, documentado y funcionando.
Fecha: 28/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------

*/
public class ListaVariables extends Bloque {

    private Tipo tipo;

    /* No ponemos generar código porque ya está en el bloque */
    public ListaVariables(int linea, Tipo tipo) {
        super(linea);
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }
}