/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: SentIF
Estado del código: Revisado, documentado y funcionando
Fecha: 06/02/2025
Versión: 2.0
-----------------------------------------------------------------------------------------
*/

import java.text.ParseException;

public class SentIF extends Instruccion {

    private Instruccion condicionDelIf;
    private Instruccion cuerpoDelIf;
    private Instruccion cuerpoDelElse;
    //                                  if (______) { }         if () { ______ }            else { ________ }
    public SentIF(int linea, Instruccion condicionDelIf, Instruccion cuerpoDelIf, Instruccion cuerpoDelElse) {
        super(linea);
        this.condicionDelIf = condicionDelIf;
        this.cuerpoDelIf = cuerpoDelIf;
        this.cuerpoDelElse = cuerpoDelElse;
    }

    /**
     * Generates the code corresponding to an if statement, which includes the evaluation
     * of the condition, the code for the true branch, and the code for the false branch
     * if an else block is present. The method also handles necessary label generation
     * for managing the flow control of the conditional statement.
     *
     * @return An object representing the current state of code generation for the if statement.
     *         If no meaningful object is generated, it returns null.
     * @throws Exception If an error occurs during code generation, such as when the condition
     *                   is not a valid instance or the condition cannot be evaluated.
     */
    @Override
    public Objeto generarCodigo() throws Exception {

        Objeto condicionObjeto = condicionDelIf.generarCodigo();
        String etqFalse = Objeto.newEtiqueta() ;
        String etqFinal = Objeto.newEtiqueta() ;

        if (!(condicionObjeto instanceof Instancia)) {
            throw new ParseException("ERROR (SentIF.java): La expresión del if debe ser una instancia (literal o variable) "
                    , getLinea()) ;
        }

        // Intentamos convertirlo (castearlo) a booleano
        /*
        if(((Instancia) expresionObjeto).getTipo() != TipoBool.instancia) {
            expresionObjeto = expresionObjeto.generarCodigoMetodo(Metodos.CAST, new Objeto[]{TipoBool.instancia}, getLinea());
        }
        */

        /*
        El generar código CTD del if y el if-else no se parece mucho al del profe
         */

        PLXC.out.println("\t"+"if (" + condicionObjeto.getIDC() + " == 0) goto " + etqFalse + ";"); // if (exp == 0) goto etqFalse;

        // Genera el código del cuerpo del if
        cuerpoDelIf.generarCodigo();

        PLXC.out.println("\t"+"goto " + etqFinal + ";"); // goto etqFinal;

        PLXC.out.println(etqFalse + ":");

        // Si el if tiene else, genera el código del cuerpo del else
        if(cuerpoDelElse != null) cuerpoDelElse.generarCodigo();

        PLXC.out.println(etqFinal + ":");

        return null;
    }
}
