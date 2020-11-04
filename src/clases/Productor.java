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

        while(ultimoNonce<(long)Math.pow(2,32) && !buffer.getHayNonce()){
            long primerNonce;
         if(ultimoNonce == 0){
            primerNonce=ultimoNonce;
         } else {
             primerNonce = this.ultimoNonce+1;
         }
            for (long i = 0; i < tamanioRango -1; i++) {
            this.ultimoNonce++;
        }
        buffer.addWorkingUnit(primerNonce, ultimoNonce);
        }
    }
}
