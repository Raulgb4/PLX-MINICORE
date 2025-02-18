/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: PLXC.java
Estado del código: Revisado, documentado y funcionando.
Fecha: 28/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------


*/


import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.util.*;

public class PLXC {

    // out es un flujo de salida que se usará para mostrar resultados
    public static PrintStream out ;

    /**
     * Es un array de identificadores de variable
     *
     * A static array list used for storing strings within the PLXC class.
     * This variable likely serves as a data structure to hold and manage
     * a collection of string values used across different methods in the class.
     */
    private static ArrayList<String> array = new ArrayList<>();

    /**
     * A static integer used as an auxiliary index within the PLXC class.
     * It is initialized to -1 and likely serves to track or manage auxiliary operations.
     */
    private static int auxIndex = -1;

    /**
     * Represents an auxiliary tag used in the logic of the program.
     * This variable is commonly utilized for generating or managing unique tags
     * for internal operations or logic processes.
     * Initialized with a default value of -1.
     */
    private static int auxTag = -1;

    // Se inicializa el objeto de la clase Yylex que es un analizador léxico.
    public static Yylex lex;

    // El main es el típico que hemos usado siempre en las prácticas y parciales
    // RARO que haya que tocarlo para algo
    public static void main(String[] argv) {

        try{

            // Leer de teclado (Flujo de entrada apuntando al teclado)
            Reader in = new InputStreamReader (System.in) ;

            // Mostrar por pantalla (Flujo de salida apuntando a la pantalla)
            out = System.out ;

            /*
			Si se pasa al menos un argumento (argv.length > 0), el primer argumento (argv[0])
			es tomado como el nombre de un archivo, y el programa reconfigura el lector (in)
			para leer desde ese archivo en lugar de leer del teclado. Se utiliza un FileReader
			para esto, que permite leer desde archivos.
			*/
            if (argv.length > 0) {
                in = new FileReader(argv[0]) ;
            }

			/*
			Si se pasa un segundo argumento (argv.length > 1), el programa redirige la salida
			desde la pantalla a un archivo cuyo nombre es el segundo agumento (argv[1]). Se utiliza
			FileOutputStream que escribe en el archivo especificado.
			*/
            if (argv.length > 1) {
                out = new PrintStream(new FileOutputStream(argv[1])) ;
            }

            /*
			Se crea un objeto de la clase Yylex (generada al hacer jflex suma.lex) que es un analizador léxico.
			El lector in (que puede ser la entrada de teclado o un archivo),
			se pasa como parámetro para que Yylex procese la entrada.

				IN    ----->    LEX     ----->     PARSER
			*/

            lex = new Yylex (in) ;

            // El error del parser ya nos aparecía en las prácticas de análisis semántico y funcionaba al ejecutar.

            // Crea un analizador sintáctico que sacará los tokens del léxico por eso se lo pasamos como parámetro
            parser p = new parser( lex );

            // Se activa el mecanismo de CUP para analizar tu fichero de entrada
            Instruccion result = (Instruccion) p.parse().value;
            result.generarCodigo();

        } catch (ParseException e) {
            /*
            Si ocurre una falta de acceso al archivo, el programa captura la excepción
            y muestra un mensaje de error.
            */
            System.err.println("\u001B[31m" + "ERROR (PLXC.java): Ha ocurrido un error durante la ejecución del analizador, "
                    + "línea de entrada " + e.getErrorOffset() + ": " + "\u001B[0m") ;
            e.printStackTrace();

            out.println();

            System.exit(-1);

        } catch (Exception e) {
            System.err.println("\u001B[31m" + "ERROR (PLXC.java): Ha ocurrido un error: " + "\u001B[0m") ;
            e.printStackTrace();
            System.exit(-1);
        }
    }
}