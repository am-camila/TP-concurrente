package clases;

public class Productor extends Thread {

    private int ultimoNonce;
    private final Buffer buffer;
    private final int tamanioRango;

    public Productor(Buffer buffer, int tamanio){
     this.buffer= buffer;
     this.ultimoNonce= 0;
     this.tamanioRango=tamanio;
 }
     public synchronized void run() {

         /*n es menor a cant threads*/
        while(true){
        int primerNonce = this.ultimoNonce;
        for (int i = 0; i < tamanioRango; i++) {
            this.ultimoNonce++;
        }
        buffer.addWorkingUnit(primerNonce, ultimoNonce);
        System.out.println(ultimoNonce);
    }}
}
