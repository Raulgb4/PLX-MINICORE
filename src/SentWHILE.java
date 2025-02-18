/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: SentWHILE
Estado del código: Revisado, documentado y funcionando
Fecha: 06/02/2025
Versión: 2.0
-----------------------------------------------------------------------------------------
*/

import java.text.ParseException ;

public class SentWHILE extends Instruccion {

    private Instruccion condicion ;
    private Instruccion cuerpo ;

    public SentWHILE(int linea, Instruccion condicion, Instruccion cuerpo) {
        super(linea);
        this.condicion = condicion ;
        this.cuerpo = cuerpo ;
    }

    @Override
    public Objeto generarCodigo() throws Exception {

        Objeto condicionObjeto ;
        String etqComienzo = Objeto.newEtiqueta() ;
        String etqFinal = Objeto.newEtiqueta() ;

        PLXC.out.println(etqComienzo + ":"); // etqComienzo:

        condicionObjeto = condicion.generarCodigo() ;

        if (!(condicionObjeto instanceof Instancia)) {
            throw new ParseException("ERROR (SentWHILE.java): La expresión del while debe ser una instancia (literal o variable) "
                    , getLinea()) ;
        }

        // Intentamos convertirlo (castearlo) a booleano
        /*
        if(((Instancia) expresionObjeto).getTipo() != TipoBool.instancia) {
            expresionObjeto = expresionObjeto.generarCodigoMetodo(Metodos.CAST, new Objeto[]{TipoBool.instancia}, getLinea());
        }
        */

        PLXC.out.println("\t"+"if (" + condicionObjeto.getIDC() + " == 0) goto " + etqFinal + ";"); // if (exp == 0) goto etqFinal;

        // Genera el código del cuerpo del while
        cuerpo.generarCodigo();

        // Siguiente iteración
        PLXC.out.println("\t"+"goto " + etqComienzo + ";"); // goto etqComienzo;

        PLXC.out.println(etqFinal + ":");

        return null;
    }
}
