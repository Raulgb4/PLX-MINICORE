/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: Etiqueta
Estado del código: Revisado, documentado y funcionando
Fecha: 30/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------

Las etiquetas que usamos en sentencias de selección o bucles:
L0, L1, L2,...

 */

import java.text.ParseException;

public class Etiqueta extends Objeto {
    private boolean usada ;

    public Etiqueta(String nombre, int numBloque) {
        super(nombre,numBloque,false);
        this.usada = false ;
    }

    @Override
    public Objeto generarCodigoMetodo(String metodo, Objeto[] params, int linea) throws Exception {
        switch(metodo) {
            case Metodos.PONER_ETQ:
                if(usada) {
                    throw new ParseException("ERROR (Etiqueta.java): Etiqueta (" + getNombreObjeto()
                            + ") ya se ha usado", linea);
                }
                PLXC.out.println(getNombreObjeto() + ": ");
                break;
            case Metodos.SALTAR_ETQ:
                PLXC.out.println("goto " + getNombreObjeto() + ";");
                break;
            default:
                throw new UnsupportedOperationException("ERROR (Etiquetas.java): El método: " + metodo +
                        " no es un método válido para etiquetas");
        }
        return null;
    }
}
