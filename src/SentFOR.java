/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: SentFOR
Estado del código: Revisado, documentado y funcionando
Fecha: 30/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------


*/

import java.text.ParseException;

public class SentFOR extends Instruccion {

    private Instruccion inicio;
    private Instruccion condicion;
    private Instruccion actualizacion;
    private Instruccion cuerpo;

    SentFOR(int linea, Instruccion inicio, Instruccion condicion, Instruccion actualizacion, Instruccion cuerpo) {
        super(linea);
        this.inicio = inicio;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.cuerpo = cuerpo;
    }

    @Override
    public Objeto generarCodigo() throws Exception {

        Objeto condicionObjeto;
        String etqComienzo = Objeto.newEtiqueta();
        String etqFinal = Objeto.newEtiqueta();

        if (inicio != null) inicio.generarCodigo();

        PLXC.out.println(etqComienzo + ":"); // etqComienzo:

        if (condicion != null) {
            condicionObjeto = condicion.generarCodigo();

            if (!(condicionObjeto instanceof Instancia)) {
                throw new ParseException("ERROR (SentFOR.java): La condición del for debe ser una instancia (literal o variable)"
                        , getLinea());
            }

            // Intentamos convertirlo (castearlo) a booleano
            /*
            if(((Instancia) expresionObjeto).getTipo() != TipoBool.instancia) {
                expresionObjeto = expresionObjeto.generarCodigoMetodo(Metodos.CAST, new Objeto[]{TipoBool.instancia}, getLinea());
            }
            */

            PLXC.out.println("\t" + "if (" + condicionObjeto.getIDC() + " == 0) goto " + etqFinal + ";"); // if (exp == 0) goto etqFin
        }

        if (cuerpo != null) cuerpo.generarCodigo();
        if (actualizacion != null) actualizacion.generarCodigo();
        PLXC.out.println("\t" + "goto " + etqComienzo + ";"); // goto etqComienzo

        PLXC.out.println(etqFinal + ":"); // etqFinal:

        return null;
    }

}
