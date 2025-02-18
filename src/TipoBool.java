/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: TipoBool
Estado del código: Revisado, documentado y funcionando
Fecha: 30/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------


*/

import java.text.ParseException;

public class TipoBool extends Tipo {

    public static final TipoBool instancia = new TipoBool();
    private final String TRUE = "true";
    private final String FALSE = "false";

    private TipoBool() {
        super(TiposPredefinidos.BOOLEANO, 0, false);
    }

    @Override
    public Objeto generarCodigoInstancia(Instancia instancia, String metodo, Objeto[] params, int linea) throws Exception {

        Objeto obj;
        Tipo tipo;
        Variable var;
        Etiqueta etq;

        switch (metodo) {
            case Metodos.MOSTRAR:
                // if (a == 0) write "false"
                // else write "true"
                String etqFinal = newEtiqueta();
                String etqFalse = newEtiqueta();

                PLXC.out.println("\t"+"if (" + instancia.getIDC() + " == 0) goto " + etqFalse + ";");

                for(int i = 0; i < TRUE.length(); i++) {
                    PLXC.out.println("\t"+"writec " + Character.codePointAt(TRUE, i) + ";");
                }
                PLXC.out.println("\t"+"goto " + etqFinal + ";");

                PLXC.out.println(etqFalse + ":");
                for(int i = 0; i < FALSE.length(); i++) {
                    PLXC.out.println("\t"+"writec " + Character.codePointAt(FALSE, i) + ";");
                }

                PLXC.out.println(etqFinal + ":");
                break; // Ojo con este break

            case Metodos.ASIGNAR: // a = ....
                if(!instancia.isMutable()) {
                    throw new ParseException("ERROR (TipoBool.java): No se pudo reasignar un valor a la constante <"
                            + instancia.getNombreObjeto() + ">", linea);
                }
            case Metodos.CREAR_VARIABLE:

                if (params == null) {
                    throw new ParseException("ERROR (TipoBool.java): No se han pasado parámetros para el método "
                            + metodo, linea);
                }

                obj = params[0];

                if (!(obj instanceof Instancia)) {
                    // 2º argumento de la excepción podría ser getNumBloque en vez de linea
                    // x = ;
                    throw new ParseException("ERROR (TipoBool.java): (" + obj.getNombreObjeto()
                            + " ) no es una instancia (literal o variable)", linea);
                }

                tipo = ((Instancia) obj).getTipo();

                if (tipo != this) {
                    throw new ParseException("ERROR (TipoBool.java) El objeto (" + obj.getNombreObjeto()
                            + ") no es de tipo " + getNombreObjeto(), linea);
                }

                // Genera el código de salida de tres direcciones (CTD)
                // x$1 = y ;
                PLXC.out.println("\t"+instancia.getIDC() + " = " + obj.getIDC() + ";");

                // Se devuelve el resultado de la operación
                return params[0];

            case Metodos.CREAR_LITERAL:
                PLXC.out.println("\t"+instancia.getIDC() + " = " + ((Literal) instancia).getValor() + ";");
                return instancia;

            // AND y OR (Mirar Clase CortoCircuito)
            // $t0 = a;
            // $t1 = $t0 (resultado);
            // if ($t1 == 0) / ($t1 == 1) goto L;
            // $t2 = b;
            // $t1 = $t2;
            // L:
            case Metodos.AND:
            case Metodos.OR:
                if (params == null) {
                    throw new ParseException("ERROR (TipoBool.java): No se han pasado parámetros para el método "
                            + metodo, linea);
                }

                etq = (Etiqueta) params[0] ;
                var = new Variable(newNombreObjeto(), instancia.getNumBloque(), false, this);
                var.generarCodigoMetodo(Metodos.CREAR_VARIABLE, new Objeto[]{instancia}, linea); // $t1 = $t0

                switch(metodo) {
                    case Metodos.AND:
                        PLXC.out.println("\t"+"if (" + var.getIDC() + " == 0) goto " + etq.getNombreObjeto() + ";");
                        break;
                    case Metodos.OR:
                        PLXC.out.println("\t"+"if (" + var.getIDC() + " == 1) goto " + etq.getNombreObjeto() + ";");
                        break;
                }

                return var ;

            case Metodos.NOT:
                var = new Variable(newNombreObjeto(), instancia.getNumBloque(), false, this);
                PLXC.out.println("\t"+var.getIDC() + " = 1 - " + instancia.getIDC() + ";");
                return var;

            default:
                throw new ParseException("ERROR (TipoBool.java): Método " + metodo + " no permitido para " + getNombreObjeto(), linea);
        }
        return null ;
    }

    @Override
    public Objeto generarCodigoMetodo(String metodo, Objeto[] param, int linea) throws Exception {
        return null;
    }
}
