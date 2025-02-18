/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: TipoInt
Estado del código: Revisado, documentado y funcionando
Fecha: 30/01/2025
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

public class TipoInt extends Tipo {

    /**
     * A static and final instance of the class {@code TipoInt}.
     * This instance represents the predefined type {@code int} in the context of the
     * programming model implemented by this framework. It is initialized once
     * and shared across the application to ensure consistency and to enforce the
     * singleton pattern for this specific type.
     */
    public static final TipoInt instancia = new TipoInt();

    private TipoInt() {
        super(TiposPredefinidos.ENTERO, 0, false);
    }

    /**
     * Generates the corresponding code for a specified operation on an instance, such as
     * assignments, arithmetic operations, relational operations, or literals.
     *
     * @param instancia The instance object representing the primary operand of the operation for which the code is being generated.
     * @param metodo The operation or method to be performed (e.g., assignment, arithmetic operation, relational comparison).
     * @param params An array of objects representing additional parameters or operands required for the operation.
     * @param linea The line number in the source code for error reporting purposes.
     * @return The result of the operation, typically either the same instance or a new variable/literal produced by the operation.
     * @throws Exception If the operation is invalid, arguments are missing, or other runtime errors occur.
     */
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
                //                       instancia -> Primer argumento
                //                                   $t1 o tmb x$0
                PLXC.out.println("\t"+"print " + instancia.getIDC() + ";");
                break;

            // Generación de código CTD de una asignación
            case Metodos.ASIGNAR:
                // Preguntamos si es mutable o no.
                // Si no es mutable (es una constante) no se le permite generar código.
                if (!instancia.isMutable()) {
                    throw new ParseException("ERROR (TipoInt.java): (" + instancia.getNombreObjeto()
                            + " ) no es una variable", linea);
                }
                // Me encargo de asignar un valor a variable independientemente de si es constante o no
                // int a = 3 --> Es un constructor de copia. Ej: int a(3). NO una asignación.

                // Generas un código para una variable
            case Metodos.CREAR_VARIABLE:

                if (params == null) {
                    throw new ParseException("ERROR (TipoInt.java): No se han pasado parámetros para el método "
                            + metodo, linea);
                }
                // obj es el segundo argumento
                obj = params[0];

                if (!(obj instanceof Instancia)) {
                    // 2º argumento de la excepción podría ser getNumBloque en vez de linea
                    // x = ;
                    throw new ParseException("ERROR (TipoInt.java): (" + obj.getNombreObjeto()
                            + " ) no es una instancia (literal o variable)", linea);
                }

                tipo = ((Instancia) obj).getTipo();

                if (tipo != this) {
                    throw new ParseException("ERROR (TipoInt.java) El objeto (" + obj.getNombreObjeto()
                            + ") no es de tipo " + getNombreObjeto(), linea);
                }

                // Genera el código de salida de tres direcciones (CTD)
                // x$1 = y ;
                //           primer argumento (la variable)         segundo argumento (el valor que toma)
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
                    throw new ParseException("ERROR (TipoInt.java): No se han pasado parámetros para el método "
                            + metodo, linea);
                }

                obj = params[0];

                if (!(obj instanceof Instancia)) {
                    throw new ParseException("ERROR (TipoInt.java): <" + obj.getNombreObjeto()
                            + "> no es una instancia (literal o variable)", linea);
                }

                tipo = ((Instancia) obj).getTipo();

                switch (tipo.getNombreObjeto()) {
                    case TiposPredefinidos.ENTERO:
                        break;
                    default:
                        throw new ParseException("ERROR (TipoInt.java): Tipo inválido para operar con "
                                + getNombreObjeto(), linea);
                }

                // var es la variable que guarda el resultado de la operacion
                // Ejemplo: $t3$0 = x + y -> var = $t3$0 -> Variable temporal 3 en el bloque 0
                // instancia es el primer argumento de la operación
                // Ejemplo: $t3$0 = x + y -> instancia = x
                var = new Variable(newNombreObjeto(), instancia.getNumBloque(), false, this);

                //                     $t3$0        =            x
                PLXC.out.print("\t"+var.getIDC() + " = " + instancia.getIDC());

                // Ahora toca printear el operador correspondiente:

                switch (metodo) {
                    case Metodos.SUMA:
                        PLXC.out.print(" + ");
                        break;
                    case Metodos.RESTA:
                        PLXC.out.print(" - ");
                        break;
                    case Metodos.PRODUCTO:
                        PLXC.out.print(" * ");
                        break;
                    case Metodos.DIVISION:
                        PLXC.out.print(" / ");
                        break;
                }

                // Ahora toca printear el segundo argumento de la operación y el PYC

                // obj es el segundo argumento de la operación
                // Ejemplo: $t3$0 = x + y -> obj = y
                PLXC.out.println(obj.getIDC() + ";");
                //     $t3$0
                return var;

            // Operadores relacionales:
            case Metodos.IGUAL:
            case Metodos.DISTINTO:
            case Metodos.MENOR:
            case Metodos.MAYOR:
            case Metodos.MENOR_IGUAL:
            case Metodos.MAYOR_IGUAL:
                if (params == null) {
                    throw new ParseException("ERROR (TipoInt.java): No se han pasado parámetros para el método "
                            + metodo, linea);
                }

                obj = params[0];

                if (!(obj instanceof Instancia)) {
                    throw new ParseException("ERROR (TipoInt.java): <" + obj.getNombreObjeto()
                            + "> no es una instancia (literal o variable)", linea);
                }

                tipo = ((Instancia) obj).getTipo();

                if(tipo != this) { // && tipo != TipoReal.instancia
                    throw new ParseException("ERROR (TipoInt.java): Tipo inválido para comparar con " + getNombreObjeto(), linea);
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
                PLXC.out.println(instancia.getIDC() + " = " + instancia.getIDC() + " + 1" + ";");
                return instancia;

            case Metodos.ANTERIOR:
                // $t0 = a - 1;
                PLXC.out.println(instancia.getIDC() + " = " + instancia.getIDC() + " - 1" + ";");
                return instancia;

            case Metodos.OPUESTO:
                var = new Variable(newNombreObjeto(), instancia.getNumBloque(), false, this);
                // $t0 = 0 - a;
                PLXC.out.println(var.getIDC() + " = 0 -" + instancia.getIDC() + ";");

                return var ;

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
                var = new Variable(newNombreObjeto(), instancia.getNumBloque(), false, this);
                PLXC.out.println(var.getIDC() + " = " + instancia.getIDC() + ";"); // $t0 = a ;
                return var ;

            // Operadores especiales
            case Metodos.DESP_IZQUIERDA:
            case Metodos.DESP_DERECHA:

                if (params == null) {
                    throw new ParseException("ERROR (TipoInt.java): No se han pasado parámetros para el método "
                            + metodo, linea);
                }

                obj = params[0];

                if (!(obj instanceof Instancia)) {
                    throw new ParseException("ERROR (TipoInt.java): <" + obj.getNombreObjeto()
                            + "> no es una instancia (literal o variable)", linea);
                }

                tipo = ((Instancia) obj).getTipo();

                if (tipo != this) {
                    throw new ParseException("ERROR (TipoInt.java): <" + obj
                            + "> no es de tipo" + getNombreObjeto(),linea) ;
                }

                // El truco está en ir mirando el CTD resultado del profe e ir programando lo que tiene que salir.
                // No el del enunciado del examen, sino el del script.
                /*
                 PLX:

                 print(16>>2);

                 CTD:
                       $t0 = 16;
                       $t1 = 1;
                    L0:
                       if (2 < $t1) goto L1;
                       $t0 = $t0 / 2;
                       $t1 = $t1 + 1;
                       goto L0;
                    L1:
                       print $t0;
                * */

                String etqInicio = newEtiqueta(); // L0
                String etqFin = newEtiqueta();    // L1

                var = new Variable(newNombreObjeto(), instancia.getNumBloque(), false, this);

                //                                          16 >> 2
                //    instancia.getIDC es el primer argumento de la operación -> t0 = 16
                //                      $t2            =         t0   ;
                PLXC.out.println("\t"+var.getIDC() + " = " + instancia.getIDC() + ";");
                // Esta línea de aquí arriba me ha liado porque el profe no lo hace igual.

                String objNuevo = newNombreObjeto();
                //                   $t1    =  1 ;
                PLXC.out.println("\t"+objNuevo + " = 1;");
                //                  L0:
                PLXC.out.println(etqInicio + ":");
                //                                          16 >> 2
                //    obj.getIDC es el segundo argumento de la operación -> 2
                //                     if (  2                 <       $t1   ) goto      L1
                PLXC.out.println("\t"+"if(" + obj.getIDC() + " < " + objNuevo + ") goto " + etqFin + ";");

                if(metodo.equals(Metodos.DESP_IZQUIERDA)) {
                    // Si es desplazamiento izquierda <<
                    PLXC.out.println("\t"+var.getIDC() + " = " + var.getIDC() +" * 2;");
                    // $t0       =    $t0 * 2 ;
                } else {
                    // Si es desplazamiento derecha >>
                    PLXC.out.println("\t"+var.getIDC() + " = " + var.getIDC() +" / 2;");
                    // $t0       =    $t0 / 2 ;
                }
                //                     $t1      =    $t1 + 1 ;
                PLXC.out.println("\t"+objNuevo + " = " + objNuevo + " + 1;");
                //                      goto  L0;
                PLXC.out.println("\t"+"goto " + etqInicio + ";");
                //                L1 :
                PLXC.out.println(etqFin + ":");

                return var ;

            case Metodos.MODULO:

                if (params == null) {
                    throw new ParseException("ERROR (TipoInt.java): No se han pasado parámetros para el método "
                            + metodo, linea);
                }

                obj = params[0];

                if (!(obj instanceof Instancia)) {
                    throw new ParseException("ERROR (TipoInt.java): <" + obj.getNombreObjeto()
                            + "> no es una instancia (literal o variable)", linea);
                }

                // Esto de aquí abajo es la primera vez que lo veo así

                // $t0 = a / b;
                Objeto cociente = instancia.generarCodigoMetodo(Metodos.DIVISION, params, linea);
                // $t1 = $t0 * b;
                Objeto multiplicacion = cociente.generarCodigoMetodo(Metodos.PRODUCTO, params, linea);
                // $t2 = a - $t1
                return instancia.generarCodigoMetodo(Metodos.RESTA, new Objeto[]{multiplicacion}, linea);

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
                throw new ParseException("ERROR (TipoInt.java): Método " + metodo + " no permitido para " + getNombreObjeto(), linea);
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
