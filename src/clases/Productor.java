package clases;

import java.util.Date;

public class Productor extends Thread {

    private long ultimoNonce;
    private final Buffer buffer;
    private final long tamanioRango;

    public Productor(Buffer buffer, long tamanio){
     this.buffer= buffer;
     this.ultimoNonce= 0;
     this.tamanioRango=tamanio;
 }
     public void run() {

        while(ultimoNonce<Math.pow(2,32) && !buffer.getHayNonce()){
        long primerNonce = this.ultimoNonce;
            for (int i = 0; i < tamanioRango; i++) {
            this.ultimoNonce++;
        }
        buffer.addWorkingUnit(primerNonce, ultimoNonce);
        System.out.println("se termino de producir un rango. ultimo nonce:"+ultimoNonce);
        }
    }
}
