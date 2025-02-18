/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: Metodos
Estado del código: Revisado, documentado y funcionando.
Fecha: 28/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------
Objetivo del código: The Metodos class contains a series of String constants that represent
symbolic names for a set of common operations and logical expressions.
These constants serve as placeholders or identifiers for specific operations,
enabling a more readable and maintainable code base, where symbolic names
can be used in place of traditional operators or logic symbols.

*/
public class Metodos {

    public static final String CREAR_LITERAL = "$crear_literal"   ; // Construir un literal
    public static final String CREAR_VARIABLE = "$crear_variable" ; // Construir una variable
    public static final String ASIGNAR = "$asignar"               ; // Asignar "="

    // Operadores aritméticos básicos:

    public static final String SUMA = "$suma"        ; // Suma "+"
    public static final String RESTA = "$resta"      ; // Resta "-"
    public static final String PRODUCTO = "$producto"; // Multiplicación "*"
    public static final String DIVISION = "$division"; // División "/"

    // Operadores relacionales:

    public static final String IGUAL       = "$igual"       ; // ==
    public static final String DISTINTO    = "$distinto"    ; // !=
    public static final String MAYOR       = "$mayor"       ; // >
    public static final String MENOR       = "$menor"       ; // <
    public static final String MAYOR_IGUAL = "$mayor_igual" ; // >=
    public static final String MENOR_IGUAL = "$menor_igual" ; // <=

    // Operadores lógicos (booleanos):

    public static final String AND = "$and" ; // &&
    public static final String OR  = "$or"  ; // ||
    public static final String NOT = "$not" ; // !

    // Operadores especiales:

    public static final String OPUESTO = "$opuesto"               ; // Opuesto (menos unario) -3
    public static final String MAS_UNARIO = "$masunario"          ; // Mas unario +x -> No afecta al valor de la variable
    public static final String SIGUIENTE = "$siguiente"           ; // Valor siguiente x++ o ++x
    public static final String ANTERIOR = "$anterior"             ; // Valor anterior  x--  o --x
    public static final String DESP_IZQUIERDA = "$desp_izquierda" ; // <<
    public static final String DESP_DERECHA = "$desp_derecha"     ; // >>
    public static final String MODULO = "$modulo"                 ; // %
    public static final String MAS_IGUAL = "mas_igual"            ; // +=
    public static final String MENOS_IGUAL = "menos_igual"        ; // +=
    public static final String MULT_IGUAL = "mult_igual"          ; // *=
    public static final String DIV_IGUAL = "div_igual"            ; // /=


    // Mostrar por pantalla:

    public static final String MOSTRAR = "$mostrar"; // El "print" de siempre

    // Etiquetas

    public static final String PONER_ETQ  = "$poner_etq"  ; // Poner etiqueta en la salida
    public static final String SALTAR_ETQ = "$saltar_etq" ;
}
