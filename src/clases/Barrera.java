package clases;


  public class Barrera  {
    private int nroParaLevantarBarrera;//cambiar nombre
    private int contador = 0;

    public Barrera(int nroParaLevantarBarrera) {
        this.nroParaLevantarBarrera = nroParaLevantarBarrera;
    }

    public boolean puedeLevantarBarrera(){
        return contador == nroParaLevantarBarrera;
    }

    public synchronized void esperar(){
        this.contador++;
        while(!puedeLevantarBarrera()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
    }
}


