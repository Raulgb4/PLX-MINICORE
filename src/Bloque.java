/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: Bloque
Estado del código: Revisado, documentado y funcionando.
Fecha: 28/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------

Un bloque al fin al cabo es un conjunto ordenado de instrucciones.

*/

import java.util.Vector;

public class Bloque extends Instruccion {

    /**
     * The list of instructions contained within this block.
     * The `Vector` class is used because it can dynamically grow in size, unlike arrays.
     * Stores a sequence of objects along with their respective positions.
     * Any data structure with an intrinsic order, such as `ArrayList`, could also be used.
     */
    // Se usa la clase Vector porque los vectores pueden crecer, los arrays no.
    // Almacenar lista de objetos con su posición
    // Se podría haber usado cualquier cosa que tenga orden por ejemplo ArrayList
    protected Vector<Instruccion> instrucciones ;

    public Bloque (int linea) {
        super(linea);
        instrucciones = new Vector<>() ;
    }

    /**
     * Adds an instruction to the list of instructions in this block.
     *
     * @param instruccion the instruction to be added to the block
     */
    public void addInstruccion(Instruccion instruccion) {
        instrucciones.add(instruccion) ;
    }

    /**
     * Generates the code for the block by invoking the generarCodigo method
     * on each instruction contained within the block.
     *
     * @return Always returns null after generating code for all instructions in the block.
     * @throws Exception if an error occurs while generating code for any of the instructions.
     */
    @Override
    public Objeto generarCodigo() throws Exception {
        for(Instruccion instruccion : instrucciones) {
            instruccion.generarCodigo() ;
        }
        return null;
    }
}
