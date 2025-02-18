/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: TipoFloat
Estado del código: Revisado, documentado y funcionando
Fecha: 05/02/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------

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

public class TipoFloat extends Tipo {

    public static final TipoFloat instancia = new TipoFloat();

    private TipoFloat() {
        super(TiposPredefinidos.REAL, 0, false);
    }

    @Override
    public Objeto generarCodigoInstancia(Instancia instancia, String metodo, Objeto[] params, int linea) throws Exception {

        // instancia -> Puede ser un literal o una variable -> instancia.getIDC()
        // Metodo es la operación en cuestión

        Objeto obj;// = params[0] -> Esto es el 2º argumento de una operación
        Tipo tipo;
        Variable var;
        String etq;

        switch (metodo) {

            // Generación de código CTD del print
            case Metodos.MOSTRAR:
                PLXC.out.println("\t"+"print " + instancia.getIDC() + ";");
                break;

            // Generación de código CTD de una asignación
            case Metodos.ASIGNAR: // a = ....
                // Preguntamos si es mutable o no.
                // Si no es mutable (es una constante) no se le permite generar código.
                if (!instancia.isMutable()) {
                    throw new ParseException("ERROR (TipoFloat.java): (" + instancia.getNombreObjeto()
                            + " ) no es una variable", linea);
                }
                // Me encargo de asignar un valor a variable independientemente de si es constante o no
                // int a = 3 --> Es un constructor de copia. Ej: int a(3). NO una asignación.

                // Generas un código para una variable
            case Metodos.CREAR_VARIABLE: // float a =....

                if (params == null) {
                    throw new ParseException("ERROR (TipoFloat.java): No se han pasado parámetros para el método "
                            + metodo, linea);
                }

                obj = params[0];

                if (!(obj instanceof Instancia)) {
                    // 2º argumento de la excepción podría ser getNumBloque en vez de linea
                    // x = ;
                    throw new ParseException("ERROR (TipoFloat.java): (" + obj.getNombreObjeto()
                            + " ) no es una instancia (literal o variable)", linea);
                }

                tipo = ((Instancia) obj).getTipo();

                if (tipo != this) {
                    throw new ParseException("ERROR (TipoFloat.java) El objeto (" + obj.getNombreObjeto()
                            + ") no es de tipo " + getNombreObjeto(), linea);
                }

                // Genera el código de salida de tres direcciones (CTD)
                // x$1 = y ;
                PLXC.out.println("\t"+instancia.getIDC() + " = " + obj.getIDC() + ";");

                // Se devuelve el resultado de la operación
                return params[0];

            // $t0 = 3; Cada vez que tenemos un literal en el código, guardarla para que la podamos usar.
            // Constructor literal es llamado dede ExpresionLiteral. Y este es llamado desde
            // CUP.
            case Metodos.CREAR_LITERAL:
                // $t0 = 6 ;
                PLXC.out.println("\t"+instancia.getIDC() + " = " + ((Literal) instancia).getValor() + ";");
                return instancia;

            // Operadores aritméticos
            case Metodos.SUMA:
            case Metodos.RESTA:
            case Metodos.PRODUCTO:
            case Metodos.DIVISION:
                if (params == null) {
                    throw new ParseException("ERROR (TipoFloat.java): No se han pasado parámetros para el método "
                            + metodo, linea);
                }

                obj = params[0];

                if (!(obj instanceof Instancia)) {
                    throw new ParseException("ERROR (TipoFloat.java): <" + obj.getNombreObjeto()
                            + "> no es una instancia (literal o variable)", linea);
                }

                tipo = ((Instancia) obj).getTipo();



                // var es la variable que guarda el resultado de la operacion
                // Ejemplo: $t3$0 = x + y -> var = $t3$0 -> Variable temporal 3 en el bloque 0
                // instancia es el primer argumento de la operación
                // Ejemplo: $t3$0 = x + y -> instancia = x
                var = new Variable(newNombreObjeto(), instancia.getNumBloque(), false, this);

                PLXC.out.print("\t"+var.getIDC() + " = " + instancia.getIDC()); // $t3$0 = x

                switch (metodo) {
                    case Metodos.SUMA:
                        PLXC.out.print(" +r ");
                        break;
                    case Metodos.RESTA:
                        PLXC.out.print(" -r ");
                        break;
                    case Metodos.PRODUCTO:
                        PLXC.out.print(" *r ");
                        break;
                    case Metodos.DIVISION:
                        PLXC.out.print(" /r ");
                        break;
                }

                // obj es el segundo argumento de la operación
                // Ejemplo: $t3$0 = x + y -> obj = y
                PLXC.out.println(obj.getIDC() + ";");

                return var;

            // Operadores relacionales:
            case Metodos.IGUAL:
            case Metodos.DISTINTO:
            case Metodos.MENOR:
            case Metodos.MAYOR:
            case Metodos.MENOR_IGUAL:
            case Metodos.MAYOR_IGUAL:
                if (params == null) {
                    throw new ParseException("ERROR (TipoFloat.java): No se han pasado parámetros para el método "
                            + metodo, linea);
                }

                obj = params[0];

                if (!(obj instanceof Instancia)) {
                    throw new ParseException("ERROR (TipoFloat.java): <" + obj.getNombreObjeto()
                            + "> no es una instancia (literal o variable)", linea);
                }

                tipo = ((Instancia) obj).getTipo();

                if(tipo != this) { // && tipo != TipoReal.instancia
                    throw new ParseException("ERROR (TipoFloat.java): Tipo inválido para comparar con " + getNombreObjeto(), linea);
                }

                var = new Variable(newNombreObjeto(), instancia.getNumBloque(), false, TipoBool.instancia);
                PLXC.out.println("\t"+var.getIDC() + " = 1;"); // $t0 = 1

                // Creas una etiqueta
                etq = newEtiqueta();

                switch (metodo){
                    case Metodos.IGUAL:
                        // if (a == p) goto L ;
                        PLXC.out.println("\t"+"if (" + instancia.getIDC() + " == " + obj.getIDC() + ") goto " + etq + ";");
                        break ;
                    case Metodos.DISTINTO:
                        // if (a != p) goto L ;
                        PLXC.out.println("\t"+"if (" + instancia.getIDC() + " != " + obj.getIDC() + ") goto " + etq + ";");
                        break ;
                    case Metodos.MENOR:
                        // if (a < p) goto L ;
                        PLXC.out.println("\t"+"if (" + instancia.getIDC() + " < " + obj.getIDC() + ") goto " + etq + ";");
                        break ;
                    case Metodos.MENOR_IGUAL:
                        // if (a < p) goto L ;
                        PLXC.out.println("\t"+"if (" + instancia.getIDC() + " < " + obj.getIDC() + ") goto " + etq + ";");
                        // if (a == p) goto L ;
                        PLXC.out.println("\t"+"if (" + instancia.getIDC() + " == " + obj.getIDC() + ") goto " + etq + ";");
                        break ;
                    case Metodos.MAYOR:
                        // if (p < a) goto L ;
                        PLXC.out.println("if (" + obj.getIDC() + " < " + instancia.getIDC() + ") goto " + etq + ";");
                        break ;
                    case Metodos.MAYOR_IGUAL:
                        // if (p < a) goto L ;
                        PLXC.out.println("if (" + obj.getIDC() + " < " + instancia.getIDC()  + ") goto " + etq + ";");
                        // if (a == p) goto L ;
                        PLXC.out.println("if (" + instancia.getIDC() + " == " + obj.getIDC() + ") goto " + etq + ";");
                        break ;
                }

                PLXC.out.println("\t"+var.getIDC() + " = 0;"); // $t0 = 0 ;
                PLXC.out.println(etq + ":"); // L1:
                return var ;

            case Metodos.SIGUIENTE:
                // $t0 = a + 1;
                PLXC.out.println(instancia.getIDC() + " = " + instancia.getIDC() + " +r 1" + ";");
                return instancia;

            case Metodos.ANTERIOR:
                // $t0 = a - 1;
                PLXC.out.println(instancia.getIDC() + " = " + instancia.getIDC() + " -r 1" + ";");
                return instancia;

            case Metodos.OPUESTO:
                var = new Variable(newNombreObjeto(), instancia.getNumBloque(), false, this);
                // $t0 = 0 - a;
                PLXC.out.println(var.getIDC() + " = 0 -r" + instancia.getIDC() + ";");

                return var ;

            case Metodos.MAS_IGUAL:
                obj = instancia.generarCodigoMetodo(Metodos.SUMA,params,linea) ;
                PLXC.out.println("\t"+instancia.getIDC() + " = " + obj.getIDC() + " ;");
                return instancia ;
            case Metodos.MENOS_IGUAL:
                obj = instancia.generarCodigoMetodo(Metodos.RESTA,params,linea) ;
                PLXC.out.println("\t"+instancia.getIDC() + " = " + obj.getIDC() + " ;");
                return instancia ;
            case Metodos.MULT_IGUAL:
                obj = instancia.generarCodigoMetodo(Metodos.PRODUCTO,params,linea) ;
                PLXC.out.println("\t"+instancia.getIDC() + " = " + obj.getIDC() + " ;");
                return instancia ;
            case Metodos.DIV_IGUAL:
                obj = instancia.generarCodigoMetodo(Metodos.DIVISION,params,linea) ;
                PLXC.out.println("\t"+instancia.getIDC() + " = " + obj.getIDC() + " ;");
                return instancia ;

            default:
                throw new ParseException("ERROR (TipoFloat.java): Método " + metodo + " no permitido para " + getNombreObjeto(), linea);
        }

        return null; // Los métodos que no devuelven nada, null
    }

    /**
     * Generates code for a specified method that operates on an instance of the class
     * and its parameters. This method is part of the implementation for handling specific
     * predefined methods within the context of arrays or other data types.
     *
     * @param metodo The name of the method to generate code for.
     * @param param An array of objects representing the parameters of the method.
     * @param linea The line number in the source code where the method is being executed, used for error tracking.
     * @return An instance of {@link Objeto} containing the results or state after the method execution.
     * @throws Exception Indicates an error occurred during code generation or the specified
     *         method is not implemented.
     */
    //Esto se ha utilizado para implementar los arrays (19/12)
    @Override
    public Objeto generarCodigoMetodo(String metodo, Objeto[] param, int linea) throws Exception {

        // Se tiene que quedar vacío. El profe lo tiene vació

        /*
        Variable v ;

        switch (metodo) {
            case Metodos.SIZEOF:
                v = new Variable(newNombreObjeto(),instancia.getNumBloque(),false,this);
                PLXC.out.println(v.getIDC() + " = 1");
                return v;
            default:
                throw new ParseException("Operacion ( " + metodo + " ) no implementado",linea) ;
        }
        */

        // Para los arrays aquí vamos a añadir código.

        return null;
    }
}
