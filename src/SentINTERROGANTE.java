/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: SentINTERROGANTE
Estado del código: Revisado, documentado y funcionando
Fecha: 10/02/2025
Versión: 2.0
-----------------------------------------------------------------------------------------
*/
public class SentINTERROGANTE extends Instruccion {

    private Instruccion condicion  ;
    private Instruccion cuerpoTrue ;
    private Instruccion cuerpoFalse ;

   public SentINTERROGANTE(int linea, Instruccion condicion, Instruccion cuerpoTrue, Instruccion cuerpoFalse) {
        super(linea);
        this.condicion = condicion ;
        this.cuerpoTrue = cuerpoTrue ;
        this.cuerpoFalse = cuerpoFalse ;
   }

    @Override
    public Objeto generarCodigo() throws Exception {
    /*
        PLX:
            print ( 1 < 2 ? 3 : 4 );

        CTD:
            if (1 < 2) goto L0;
            goto L1;
        L0:
            t0 = 3;
            goto L2;
        L1:
            t0 = 4;
        L2:
            print t0;
    */
        // Se genera el código de la condición
        Objeto condObj = condicion.generarCodigo();

        // Se crean las etiquetas

        String etqTrue = Objeto.newEtiqueta() ;
        String etqFalse = Objeto.newEtiqueta() ;
        String etqFinal = Objeto.newEtiqueta() ;

        Variable temp = new Variable(Objeto.newNombreObjeto(), 0, false, TipoInt.instancia);

        PLXC.out.println("\t"+"if (" + condObj.getIDC() + " != 0) goto " + etqTrue + ";");
        PLXC.out.println("\t"+"goto " + etqFalse + ";");

        // Rama verdadera:
        PLXC.out.println(etqTrue + ":");
        Objeto trueObj = cuerpoTrue.generarCodigo();
        PLXC.out.println("\t"+temp.getIDC() + " = " + trueObj.getIDC() + ";");
        PLXC.out.println("\t"+"goto " + etqFinal + ";");

        // Rama falsa:
        PLXC.out.println(etqFalse + ":");
        Objeto falseObj = cuerpoFalse.generarCodigo();
        PLXC.out.println("\t"+temp.getIDC() + " = " + falseObj.getIDC() + ";");

        PLXC.out.println(etqFinal + ":");
        return temp;
    }
}
