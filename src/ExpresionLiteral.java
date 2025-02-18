/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: ExpresionLiteral
Estado del código: Revisado, documentado y funcionando.
Fecha: 28/01/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------

*/
public class ExpresionLiteral extends Instruccion {
    private Literal l ;

    public ExpresionLiteral(int linea, Literal l) {
        super(linea) ;
        this.l = l ;
    }

    @Override
    public Objeto generarCodigo() throws Exception {

        Objeto o1 = l.generarCodigoMetodo(Metodos.CREAR_LITERAL,null,getLinea()) ;
        return o1;
    }
}

