/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: Tabla Símbolos
Estado del código: Revisado, documentado y funcionando.
Fecha: 28/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------

*/
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class TablaSimbolos {

    /*
        El Integer representa el número de bloque "{ }"
        El String del mapa interior es el nombre de un objeto dentro de ese bloque
        El Objeto es el objeto asociado

         Integer                            String      Objeto
         (bloque)                       (nombreObjeto) (objeto)
        |-------|                      |--------------|---------|
        |   0   | -------------------> |              |         |
        |   1   | ____                 |              |         |
        |  ...  |    |                 |     ...      |   ...   |
        |-------|    |                 |--------------|---------|
                     |
                     |-------------->      String        Objeto
                                        (nombreObjeto) (objeto)
                                       |--------------|---------|
                                       |              |         |
                                       |              |         |
                                       |     ...      |   ...   |
                                       |--------------|---------|
     */
    private final Map<Integer, Map<String, Objeto>> tablaSimbolos;

    public TablaSimbolos() {
        this.tablaSimbolos = new HashMap<>();
    }

    /**
     * Adds an object to the symbol table. The object is mapped within a nested
     * structure where the outer key is the block number and the inner key is the
     * block name. If the block doesn't exist, it is created.
     *
     * Añade un objeto a la tabla de símbolos, si ya existe dicho objeto con el
     * mismo nombre en el mismo bloque, se sobreescribe.
     *
     * Para cada bloque hay una tabla de símbolos con cada una de las variables
     * añadidas en ese bloque.
     *
     * @param objeto the object to add to the symbol table; must not be null and
     *               must have a non-null block name. El objeto a añadir
     * @throws IllegalArgumentException if the object or its block name is null
     */
    public void addObjeto(Objeto objeto) {

        Integer bloque = Integer.valueOf(objeto.getNumBloque());
        Map<String, Objeto> subtabla = tablaSimbolos.get(bloque);
        if(subtabla == null) {
            Map<String, Objeto> st = new HashMap<>();
            st.put(objeto.getNombreObjeto(), objeto);
            tablaSimbolos.put(bloque, st);
        } else {
            subtabla.put(objeto.getNombreObjeto(), objeto);
        }

        /*
        if (objeto == null || objeto.getNombreObjeto() == null) {
            throw new IllegalArgumentException("ERROR (TablaSimbolos): El objeto o el nombre del objeto no pueden ser nulos");
        }
        tablaSimbolos.computeIfAbsent(objeto.getNumBloque(), k -> new HashMap<>()).put(objeto.getNombreObjeto(), objeto);
         */
    }

    /**
     * Searches for the object associated with the given block name and returns the object
     * from the block with the highest block number.
     * <p>
     *
     * CÓDGIO HECHO POR MÍ, NO SÉ SI ES CORRECTO
     *
     * @param nombreObjeto the name of the block to search for; it must not be null
     * @return the object from the block with the highest block number associated with the given name,
     * or null if the block name is not found in any block
     * @throws IllegalArgumentException if the provided block name is null
     */
    public Objeto buscarObjeto(String nombreObjeto) {
        int mayorNumBloque = 0;
        Objeto obj = null;

        if (nombreObjeto == null) {
            throw new IllegalArgumentException("ERROR (TablaSimbolos): El nombre del bloque no puede ser nulo");
        }

        for(var entry : tablaSimbolos.entrySet()) {
            if(entry.getKey() >= mayorNumBloque && entry.getValue().containsKey(nombreObjeto)) {
                mayorNumBloque = entry.getKey();
                obj = entry.getValue().get(nombreObjeto);
            }
        }

        return obj;
    }

    /**
     * Removes and returns the object associated with the specified block number and block name
     * from the symbol table. If the block becomes empty after removal, the block itself is removed
     * from the table.
     *
     * Este método no se usa mucho
     *
     * @param numBloque    the block number from which the object should be removed
     * @param nombreObjeto the name of the block associated with the object to be removed
     * @return the removed object if it existed, or null if no object was associated with
     * the given block number and block name
     */
    public Objeto borrarObjeto(int numBloque, String nombreObjeto) {
        Map<String, Objeto> subtabla = tablaSimbolos.get(numBloque);
        Objeto eliminado = null;

        if (subtabla != null) {
            eliminado = subtabla.remove(nombreObjeto);
            if (subtabla.isEmpty()) {
                tablaSimbolos.remove(numBloque);
            }
        }
        return eliminado;
    }

    /**
     * Removes the entire block associated with the specified block number from
     * the symbol table. If no block with the given number exists, no action is performed.
     *
     * Borra todos los objetos de un bloque, todos los elementos
     *
     * @param numBloque the block number of the block to be removed
     */
    public void eliminarBloque(int numBloque) {
        tablaSimbolos.remove(numBloque);
    }

    public Variable declararVariable(int linea, Variable v) throws Exception {

        Objeto otro = buscarObjeto(v.getNombreObjeto());

        if((otro != null) && (otro.getNumBloque() == v.getNumBloque())){
            // int x = 4 ;
            // int x = 5 ;
            // Error variable ya definida
            throw new ParseException("ERROR (TablaSimbolos): Variable " + v.getNombreObjeto() + " ya definida en el mismo bloque", PLXC.lex.getLine()) ;
        }
        addObjeto(v);
        return v ;
    }

    /**
     * Returns a string representation of the symbol table, detailing each block
     * and its associated objects. For each block in the table, the block number
     * is followed by the key-value pairs within that block.
     *
     * Realmente este metodo no es necesario, no se va a usar, pero esta bien para depurar.
     * Es básicamente volcar el contenido de la tabla de símbolos en una cadena.
     *
     *  HECHO POR MÍ, NO SÉ SI ESTÁ BIEN
     *
     * @return a string that represents the structure and contents of the symbol table
     */
    @Override
    public String toString() {
        return tablaSimbolos.toString();
        /*
       StringBuilder sb = new StringBuilder();
       for (Map.Entry<Integer, Map<String, Objeto>> entry : tablaSimbolos.entrySet()) {
          sb.append("Bloque ").append(entry.getKey()).append(":\n");
          for (Map.Entry<String, Objeto> subEntry : entry.getValue().entrySet()) {
               sb.append("  ").append(subEntry.getKey()).append("-> ").append(subEntry.getValue()).append("\n");
          }
       }
       return sb.toString();
         */

    }
}
