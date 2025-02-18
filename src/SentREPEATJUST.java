import java.text.ParseException;

/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: SentREPEATJUST
Estado del código: Revisado, documentado y funcionando
Fecha: 10/02/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------


*/
public class SentREPEATJUST extends Instruccion {

    private Instruccion times ;
    private Instruccion cuerpo ;

    public SentREPEATJUST(int linea, Instruccion times, Instruccion cuerpo) {
        super(linea);
        this.times = times;
        this.cuerpo = cuerpo;
    }

    @Override
    public Objeto generarCodigo() throws Exception {
    /*
        PLX:

        int n;
        n=5;
        repeat just (n) {
            print(n);
            n = n-1;
        }

        CTD:

            n = 5;
            $t1 = n;
        L0:
            $t0 = $t0 + 1;
            if ($t1 < $t0)
            goto L1;
            print n;
            $t2 = n - 1;
            n = $t2;
            goto L0;
        L1:
    */
        String etqComienzo = Objeto.newEtiqueta(); // L0
        String etqFinal = Objeto.newEtiqueta(); // L1

        String objNuevo = Objeto.newNombreObjeto(); // $t0

        Objeto objTimes = times.generarCodigo(); // Tienes que hacer esto porque las repeticiones pueden ser un x, 4, ....

        Variable var = new Variable(Objeto.newNombreObjeto(), objTimes.getNumBloque(), false, TipoInt.instancia);

        PLXC.out.println("\t"+var.getIDC() + " = " + objTimes.getIDC() + ";");

        PLXC.out.println("\t"+objNuevo + " = 1;");

        PLXC.out.println(etqComienzo + ":");

        cuerpo.generarCodigo(); // print 1 ;

        PLXC.out.println("\t"+objNuevo + " = " + objNuevo + " + 1;"); // $t0 = $t0 + 1 ;

        PLXC.out.println("\t"+"if (" + var.getIDC() + " < " + objNuevo + ") goto " + etqFinal + ";"); // if (3 < $t0) goto L1;

        PLXC.out.println("\t"+"goto " + etqComienzo + ";"); // goto L0;

        PLXC.out.println(etqFinal + ":"); // L1

        return null;
    }
}
