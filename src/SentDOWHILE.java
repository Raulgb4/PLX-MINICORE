/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: SentDOWHILE
Estado del código: Revisado, documentado y funcionando
Fecha: 30/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------


*/

import java.text.ParseException ;

public class SentDOWHILE extends Instruccion {

    private Instruccion condicion ;
    private Instruccion cuerpo ;

    public SentDOWHILE(int linea, Instruccion condicion, Instruccion cuerpo) {
        super(linea);
        this.condicion = condicion ;
        this.cuerpo = cuerpo ;
    }

    @Override
    public Objeto generarCodigo() throws Exception {

        Objeto condicionObjeto ;
        String etqComienzo = Objeto.newEtiqueta() ;

        PLXC.out.println(etqComienzo + ":"); // etqComienzo:

        // Genera el código del cuerpo del do while
        cuerpo.generarCodigo() ;

        condicionObjeto = condicion.generarCodigo() ;

        if (!(condicionObjeto instanceof Instancia)) {
            throw new ParseException("ERROR (SentDOWHILE.java): La expresión del do while debe ser una instancia (literal o variable) "
                    , getLinea()) ;
        }

        // Intentamos convertirlo (castearlo) a booleano
        /*
        if(((Instancia) expresionObjeto).getTipo() != TipoBool.instancia) {
            expresionObjeto = expresionObjeto.generarCodigoMetodo(Metodos.CAST, new Objeto[]{TipoBool.instancia}, getLinea());
        }
        */

        PLXC.out.println("\t"+"if (" + condicionObjeto.getIDC() + " != 0) goto " + etqComienzo + ";"); // if (exp != 0) goto etqComienzo;
        return null;
    }
}
