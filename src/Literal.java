/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: Literal
Estado del código: Revisado, documentado y funcionando.
Fecha: 28/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------

*/
public class Literal extends Instancia {

    // ES IGUAL QUE VARIABLE PERO MUTABLE CAMBIA Y EL NOMBRE DE VARIABLE HAY QUE GENERARLO

    private Object valor; // Puede ser Integer, Character, Double...

    /**
     * Constructs a new Literal instance with the specified block number, type, and value.
     * El número 3 en un programa no tiene nombre por tanto en el contructor de literal no
     * se le puede poner un nombre. Le asignamos el nombre de una variable literal
     * con la fábrica de nombres de objetos. Los literales son siempre inmutables.
     * @param numBloque the block number representing the context or scope of this instance
     * @param tipo the Tipo associated with this literal instance
     * @param valor the value of the literal, which can be of various object types (e.g., Integer, Character, Double)
     */
    public Literal(int numBloque, Tipo tipo, Object valor) {
        // newNombreObjeto() genera el nombre de la variable
        // Se pasa mutable false porque un literal no varía, no es mutable como una variable
        super(newNombreObjeto(),numBloque,false,tipo);
        this.valor = valor;
    }

    public Object getValor() {
        return valor;
    }

    /**
     * Generates the code for a method corresponding to a literal object.
     *
     * @param metodo the method identifier, expected to be Metodos.CONSTLITERAL
     * @param param an array of Objeto parameters, expected to be null as literals do not require parameters
     *
     * @return an Objeto representing the instance of the code generated for the literal
     *
     * @throws ParseException if the method name is not Metodos.CONSTLITERAL or if parameters are provided
     * @throws Exception if an error occurs during code generation
     */
    @Override
    public Objeto generarCodigoMetodo(String metodo, Objeto[] param, int linea) throws Exception {

        /*
         *  Para instanciar el valor de una variable (un literal, por ejemplo el 7) no
         *  necesito ningún parámetro. Número 7, escríbete por pantalla, no necesita más info.
         * */

        // ESTOS IF PUEDE QUE SEA CONVENIENTE DESCOMENTARLOS:
        /*
        if(!metodo.equals(Metodos.CONSTLITERAL)){
            throw new ParseException("ERROR (Literal): Las constantes no admiten métodos",
                PLXC.lex.getLine()) ;
        }
        */
        /*
        if(param != null) {
            throw new ParseException("ERROR (Literal): Instanciar un literal no requiere parámetros",
                PLXC.lex.getLine()) ;
        }
        */
        return getTipo().generarCodigoInstancia(this, metodo, param, linea);

        // Al final se comporta con una variable,
        // ya que no se pensará si es uma suma con
        // vbles o literales.

        // x + y == 3 + 4 en nuestro programa, lo interpreta de la misma forma

        // return getTipo().generarCodigoInstancia(this, metodo, null);
    }
}
