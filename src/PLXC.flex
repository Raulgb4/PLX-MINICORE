/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: Código flex
Estado del código: Revisado, documentado y funcionando.
Fecha: 06/02/2025
Versión: 4.0
-----------------------------------------------------------------------------------------
*/

/*
SECCIÓN IMPORTS:
*/

import java_cup.runtime.*;
import java.text.ParseException;

%%

/*
SECCIÓN DECLARACIONES:
*/

%unicode // Para el soporte de caracteres unicode
%line    // Para que funcione el getLine
%column  // Para que funcione el getColumn
%cup     // Para conectar con el archivo .cup

/*
SECCIÓN MÉTODOS EN JAVA:
*/

%{
    // Métodos JAVA para obtener la línea y columna del token por el que va
    // analizando el analizador léxico para el control derrores
	// Obtener la línea
	public int getLine(){
		return yyline ;
	}
	// Obtener la columna
	public int getColumn() {
		return yycolumn ;
	}
%}

/*
SECCIÓN VARIABLES EN FLEX:
*/

// NÚMEROS ENTEROS

// Decimales:
ENTERO_DEC = 0|([1-9][0-9]*)

// NÚMEROS REALES

// Exponente:
EXP = (e|E)[\+\-]?{ENTERO_DEC}

// Reales:
REAL = {ENTERO_DEC}\.{ENTERO_DEC}{EXP}?

// IDENTIFICADOR

IDENT = [a-zA-Z]+[0-9]*

%%

/*
SECCIÓN EXPRESIONES Y REGLAS:
*/

// Detectar caracteres delimitadores:

    "{" { return new Symbol(sym.ALL); } // Apertura llave
    "}" { return new Symbol(sym.CLL); } // Cierre llave
    "(" { return new Symbol(sym.AP) ; }
    ")" { return new Symbol(sym.CP) ; }

// Detectar otros caracteres:

    ";" { return new Symbol(sym.PYC) ; }
    "=" { return new Symbol(sym.ASIG); }
    "," { return new Symbol(sym.COMA); }

// Detectar operadores aritméticos:

    "+"  { return new Symbol(sym.MAS)  ; } // Suma
    "-"  { return new Symbol(sym.MENOS); } // Resta
    "*"  { return new Symbol(sym.MULT) ; } // Multiplicación
    "/"  { return new Symbol(sym.DIV)  ; } // División

// Detectar operadores relacionales:

    "==" { return new Symbol(sym.IGUAL)     ; }
    "!=" { return new Symbol(sym.DIST)      ; }
    ">"  { return new Symbol(sym.MAYOR)     ; }
    "<"  { return new Symbol(sym.MENOR)     ; }
    ">=" { return new Symbol(sym.MAYORIGUAL); }
    "<=" { return new Symbol(sym.MENORIGUAL); }

// Detectar operadores lógicos (booleanos):

    "&&"   { return new Symbol(sym.AND)          ; }
    "||"   { return new Symbol(sym.OR)           ; }
    "!"    { return new Symbol(sym.NOT)          ; }
    "-->"  { return new Symbol(sym.IMPLICA)      ; }
    "<-->" { return new Symbol(sym.DOBLEIMPLICA) ; }

// Detectar operadores especiales:

    "++" { return new Symbol(sym.MASMAS)        ; }
    "--" { return new Symbol(sym.MENOSMENOS)    ; }
    "<<" { return new Symbol(sym.DESPIZQUIERDA) ; }
    ">>" { return new Symbol(sym.DESPDERECHA)   ; }
    "%"  { return new Symbol(sym.MODULO)        ; }
    "+=" { return new Symbol(sym.MASIGUAL)      ; }
    "-=" { return new Symbol(sym.MENOSIGUAL)    ; }
    "*=" { return new Symbol(sym.MULTIGUAL)     ; }
    "/=" { return new Symbol(sym.DIVIGUAL)      ; }

// Detectar tipos predefinidos:

    int     { return new Symbol(sym.INT)    ; }
    boolean { return new Symbol(sym.BOOLEAN); }
    char    { return new Symbol(sym.CHAR)   ; }
    float   { return new Symbol(sym.FLOAT)  ; }

// Detectar estructuras de selección:

    if    { return new Symbol(sym.IF)          ; }
    else  { return new Symbol(sym.ELSE)        ; }
    "?"   { return new Symbol(sym.INTERROGANTE); } // valor % 2 ? impares : pares += 1;
    ":"   { return new Symbol(sym.DP)          ; } // valor % 2 ? impares : pares += 1;
    "?:"  { return new Symbol(sym.ELVIS)       ; }
    /*
        ELVIS:
        Ejemplo del operador elvis:
            var variable = foo ?: bar
        Devuelve foo si foo existe y no es nulo sino devuelve bar

    */

// Detectar estructuras de iteración (bucles):

    do     { return new Symbol(sym.DO)      ; }
    while  { return new Symbol(sym.WHILE)   ; }
    for    { return new Symbol(sym.FOR)     ; }
    repeat { return new Symbol(sym.REPEAT)  ; }
    times  { return new Symbol(sym.TIMES)   ; }
    just   { return new Symbol(sym.JUST)    ; }

// Detectar sentencias de salida por pantalla:

    print { return new Symbol(sym.PRINT); }

// Detectar números y devolver el número detectado:

    {ENTERO_DEC} { return new Symbol(sym.ENTERO, Integer.valueOf(yytext())); }
    {REAL}       { return new Symbol(sym.REAL, Float.valueOf(yytext()))    ; }

// Caracteres

// La función codePointAt devuelve el código unicode

    \'\\n\'
    {
        return new Symbol(sym.CARACTER, Integer.valueOf(Character.codePointAt("\n", 0)));
    }

    \'\\b\'
    {
        return new Symbol(sym.CARACTER, Integer.valueOf(Character.codePointAt("\b", 0)));
    }

    \'\\f\'
    {
        return new Symbol(sym.CARACTER, Integer.valueOf(Character.codePointAt("\f", 0)));
    }

    \'\\t\'
    {
        return new Symbol(sym.CARACTER, Integer.valueOf(Character.codePointAt("\t", 0)));
    }

    \'\\r\'
    {
        return new Symbol(sym.CARACTER, Integer.valueOf(Character.codePointAt("\r", 0)));
    }

    \'\\\'\'
    {
        return new Symbol(sym.CARACTER, Integer.valueOf(Character.codePointAt("\'", 0)));
    }

    \'\\\"\'
    {
        return new Symbol(sym.CARACTER, Integer.valueOf(Character.codePointAt("\"", 0)));
    }

    \'\\\\\'
    {
        return new Symbol(sym.CARACTER, Integer.valueOf(Character.codePointAt("\\", 0)));
    }

    \'\\u[0-9a-f]{4}\'
    {
        String s = yytext().substring(3, yytext().length()-1);
        return new Symbol(sym.CARACTER, Integer.valueOf(s, 16));
    }

    \'.\' {
        String s = yytext().substring(1, yytext().length()-1);
        return new Symbol(sym.CARACTER, Integer.valueOf(Character.codePointAt(s, 0)));
    }

    // Detectar true y false

    true  { return new Symbol(sym.BOOLEANO, Integer.valueOf(1)) ;}
    false { return new Symbol(sym.BOOLEANO, Integer.valueOf(0)) ;}

    // Detectar identificadores de variable y devolver el identificador detectado:

    {IDENT} { return new Symbol(sym.IDENT, yytext()); }

    // Ignorar caracteres de espacio y salto del línea

    \s {}
    \R {}

    // Detectar caracteres inesperados:

    [^] {
          throw new RuntimeException("\u001B[33m" +"ERROR (PLXC.flex): # Error Léxico caracter inesperado: <"
                                     +yytext()+"> en la línea " + getLine()
                                     + " y la columna " + getColumn() + "\u001B[0m");
      }