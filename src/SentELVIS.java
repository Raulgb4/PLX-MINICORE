/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: SentELVIS
Estado del código: Revisado, documentado y funcionando
Fecha: 10/02/2025
Versión: 2.0
-----------------------------------------------------------------------------------------
*/
public class SentELVIS extends Instruccion {

    private Instruccion cuerpoTrue ;
    private Instruccion cuerpoFalse ;

    public SentELVIS(int linea, Instruccion cuerpoTrue, Instruccion cuerpoFalse) {
        super(linea);
        this.cuerpoTrue = cuerpoTrue ;
        this.cuerpoFalse = cuerpoFalse ;
    }

    @Override
    public Objeto generarCodigo() throws Exception {

        // Generar código del cuerpo:
        Objeto exp1Obj = cuerpoTrue.generarCodigo();
        Objeto exp2Obj = cuerpoFalse.generarCodigo();

        String etq0 = Objeto.newEtiqueta();
        String etqFin = Objeto.newEtiqueta();

        Variable temp = new Variable(Objeto.newNombreObjeto(), 0, false, TipoInt.instancia);

        // Se evalúa exp1, si es distinto de 0, se usa exp1
        PLXC.out.println(temp.getIDC() + " = " + exp1Obj.getIDC() + ";");
        PLXC.out.println("\t"+"if (" + temp.getIDC() + " != 0) goto " + etqFin + ";");

        // Si exp1 es 0, se evalúa exp2
        PLXC.out.println(etq0 + ":");
        PLXC.out.println("\t"+temp.getIDC() + " = " + exp2Obj.getIDC() + ";");

        PLXC.out.println(etqFin + ":");
        return temp;
    }
}
