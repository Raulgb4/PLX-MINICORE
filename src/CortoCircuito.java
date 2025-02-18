/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: CortoCircuito
Estado del código: Revisado, documentado y funcionando
Fecha: 30/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------

Esta clase se utiliza para implementar el cortocircuito de las operaciones lógicas
AND (&&) y OR (||). Si en un x && y, x es falso, no se evalúa y.

*/
public class CortoCircuito extends Instruccion {
    private Instruccion instrucA, instrucB;
    private String metodo;

    public CortoCircuito(int linea, Instruccion a, String metodo, Instruccion b) {
        super(linea);
        this.instrucA = a;
        this.metodo = metodo;
        this.instrucB = b;
    }

    @Override
    public Objeto generarCodigo() throws Exception {
        Objeto objA = instrucA.generarCodigo(); // -> $t0

        Etiqueta etq = new Etiqueta(Objeto.newEtiqueta(), objA.getNumBloque());

        // ($t1 = $t0; if($t0 == 0) goto L) -> $t1
        Objeto result = objA.generarCodigoMetodo(metodo, new Objeto[]{etq}, getLinea());

        Objeto objB = instrucB.generarCodigo(); // -> $t2

        result.generarCodigoMetodo(Metodos.CREAR_VARIABLE, new Objeto[]{objB}, getLinea()); // $t1 = $t2

        etq.generarCodigoMetodo(Metodos.PONER_ETQ, null, getLinea()); // L: ...

        return result;
    }
}
