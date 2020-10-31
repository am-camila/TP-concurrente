package clases;

public class Productor extends Thread {

    private long ultimoNonce;
    private final Buffer buffer;
    private final int tamanioRango;

    public Productor(Buffer buffer, int tamanio){
     this.buffer= buffer;
     this.ultimoNonce= 0;
     this.tamanioRango=tamanio;
 }
     public void run() {

        while(ultimoNonce<Math.pow(2,32)){
        long primerNonce = this.ultimoNonce;
            for (int i = 0; i < tamanioRango; i++) {
            this.ultimoNonce++;
        }
        buffer.addWorkingUnit(primerNonce, ultimoNonce);
        System.out.println(ultimoNonce);
    }}
}
