import java.text.ParseException;

/*
-----------------------------------------------------------------------------------------
Nombre y apellidos: Raúl García Balongo
Curso: 3ºB Ingeniería informática
Objetivo del código: SentREPEAT
Estado del código: Revisado, documentado y funcionando
Fecha: 03/02/2025
Versión: 1.0
-----------------------------------------------------------------------------------------

NOTA IMPORTANTE
---------------


*/
public class SentREPEAT extends Instruccion {

    private Instruccion times ;
    private Instruccion cuerpo ;

    public SentREPEAT(int linea, Instruccion times, Instruccion cuerpo) {
        super(linea);
        this.times = times;
        this.cuerpo = cuerpo;
    }

    @Override
    public Objeto generarCodigo() throws Exception {
    /*
        PLX:

        repeat print(1); 3 times;

        CTD:

        $t0 = 1;

        L0:
            print 1;
            $t0 = $t0 + 1;
            if (3 < $t0) goto L1;
            goto L0;
        L1:
    */
        String etqComienzo = Objeto.newEtiqueta() ; // L0
        String etqFinal = Objeto.newEtiqueta() ; // L1

        // Cuidado con esta línea porque no la hemos hecho nunca antes
        // String objNuevo = Objeto.newNombreObjeto() ; // $t0
        // Y en cada objNuevo.getIDC() -> objNuevo directamente.

        Variable objNuevo = new Variable(Objeto.newNombreObjeto(),0,false,TipoInt.instancia) ;

        PLXC.out.println("\t"+ objNuevo.getIDC() + " = 1;"); // $t0 = 1 ;
        PLXC.out.println(etqComienzo + ":"); // L0:

        cuerpo.generarCodigo(); // print 1 ;

        PLXC.out.println("\t"+ objNuevo.getIDC() + " = " + objNuevo.getIDC() + " + 1 ;"); // $t0 = $t0 + 1 ;

        Objeto objTimes = times.generarCodigo(); // Tienes que hacer esto porque las repeticiones pueden ser un x, 4, ....

        PLXC.out.println("\t"+"if (" + objTimes.getIDC() + " < " + objNuevo.getIDC() + ") goto " + etqFinal + ";"); // if (3 < $t0) goto L1;

        PLXC.out.println("\t"+"goto " + etqComienzo + ";"); // goto L0;

        PLXC.out.println(etqFinal + ":"); // L1:

        return null;
    }
}
