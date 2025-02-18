/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: TipoChar
Estado del código: Revisado, documentado y funcionando
Fecha: 05/02/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------

FUNCIONAMIENTO DE UN SWITCH EN JAVA
------------------------------------

 */
/**
 * Constructs a new singleton instance of TipoInt, representing an integer data
 * type.
 * This private constructor initializes the instance with predefined attributes
 * specific to integer types.
 * <p>
 * It calls the superclass constructor with the following parameters:
 * - The identifier for the integer type from the Predefinidos class.
 * - A block number of 0, indicating the default or initial scope.
 * - A mutability flag set to false, indicating the type is immutable.
 * <p>
 * Usage of this constructor is restricted to within the class, ensuring that
 * only one instance of TipoInt is created and accessed through the public
 * static field.
 * <p>
 * El constructor es privado porque solo quiero un objeto que represente el tipo
 * entero.
 * Esto se conoce como SINGLETON.
 */

import java.text.ParseException;

public class TipoChar extends Tipo {

    public static final TipoChar instancia = new TipoChar();

    public TipoChar() {
        super(TiposPredefinidos.CARACTER, 0, false);
    }

    @Override
    public Objeto generarCodigoInstancia(Instancia instancia, String metodo, Objeto[] params, int linea) throws Exception {
        Objeto obj;
        Tipo tipo;
        Variable var;

        switch (metodo) {
            case Metodos.MOSTRAR:
                PLXC.out.println("\t" + "printc " + instancia.getIDC() + ";");
                break;
            case Metodos.ASIGNAR: // a = ....
                // Preguntamos si es mutable o no.
                // Si no es mutable (es una constante) no se le permite generar código.
                if (!instancia.isMutable()) {
                    throw new ParseException("ERROR (TipoChar.java): (" + instancia.getNombreObjeto()
                            + " ) no es una variable", linea);
                }
            case Metodos.CREAR_VARIABLE: // char a = ....

                if (params == null) {
                    throw new ParseException("ERROR (TipoChar.java): No se han pasado parámetros para el método "
                            + metodo, linea);
                }

                obj = params[0];

                if (!(obj instanceof Instancia)) {
                    // 2º argumento de la excepción podría ser getNumBloque en vez de linea
                    // x = ;
                    throw new ParseException("ERROR (TipoChar.java): (" + obj.getNombreObjeto()
                            + " ) no es una instancia (literal o variable)", linea);
                }

                tipo = ((Instancia) obj).getTipo();
                if (tipo != this) {
                    throw new ParseException("ERROR (TipoInt.java) El objeto (" + obj.getNombreObjeto()
                            + ") no es de tipo " + getNombreObjeto(), linea);
                }

                // Genera el código de salida de tres direcciones (CTD)
                // x$1 = y ;
                PLXC.out.println("\t" + instancia.getIDC() + " = " + obj.getIDC() + ";");

                // Se devuelve el resultado de la operación
                return params[0];

            case Metodos.CREAR_LITERAL:
                // $t0 = 6 ;
                PLXC.out.println("\t" + instancia.getIDC() + " = " + ((Literal) instancia).getValor() + ";");
                return instancia;

            case Metodos.SUMA:
            case Metodos.RESTA:
                if (params == null) {
                    throw new ParseException("ERROR (TipoChar.java): No se han pasado parámetros para el método "
                            + metodo, linea);
                }

                obj = params[0];

                if (!(obj instanceof Instancia)) {
                    throw new ParseException("ERROR (TipoChar.java): <" + obj.getNombreObjeto()
                            + "> no es una instancia (literal o variable)", linea);
                }

                tipo = ((Instancia) obj).getTipo();

                if (tipo != TipoInt.instancia && metodo.equals(Metodos.SUMA)) {
                    throw new ParseException("ERROR (TipoChar.java): Método " + metodo + " no aplicable entre " + getNombreObjeto() + " y " + tipo.getNombreObjeto(), linea);
                }

                // var es la variable que guarda el resultado de la operacion
                // Ejemplo: $t3$0 = x + y -> var = $t3$0 -> Variable temporal 3 en el bloque 0
                // instancia es el primer argumento de la operación
                // Ejemplo: $t3$0 = x + y -> instancia = x
                var = new Variable(newNombreObjeto(), instancia.getNumBloque(), false, TipoInt.instancia);

                PLXC.out.print("\t" + var.getIDC() + " = " + instancia.getIDC());


                switch (metodo) {
                    case Metodos.SUMA:
                        PLXC.out.print(" + ");
                        break;
                    case Metodos.RESTA:
                        PLXC.out.print(" - ");
                        break;
                }
                // obj es el segundo argumento de la operación
                // Ejemplo: $t3$0 = x + y -> obj = y
                PLXC.out.println(obj.getIDC() + ";");
                return var;

            case Metodos.MAS_UNARIO:
                // Recuerda que el más unario no afecta de ninguna forma al valor de la variable.
                // es el +x indica que la variable es positiva.
                /*
                PLX:
                    int x;
                    x = 3 * -2 + +5 * 6;
                    print(x);
                CTD:
                    $t0 = -2;
                    $t1 = 3 * $t0;
                    $t2 = 5 * 6;
                    $t3 = $t1 + $t2;
                    x = $t3;
                    print x;
                */
                var = new Variable(newNombreObjeto(), instancia.getNumBloque(), false, TipoInt.instancia);
                PLXC.out.println(var.getIDC() + " = " + instancia.getIDC() + ";"); // $t0 = a ;
                return var ;

            default:
                throw new ParseException("ERROR (TipoChar.java): Método " + metodo + " no permitido para " + getNombreObjeto(), linea);
        }

        return null;
    }

    @Override
    public Objeto generarCodigoMetodo(String metodo, Objeto[] param, int linea) throws Exception {
        return null;
    }
}
