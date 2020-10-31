package clases;


    public class BarreraG  {
        private int nroParaLevantarBarrera;
        private int contador =0;

        public BarreraG(int nroParaLevantarBarrera) {
            this.nroParaLevantarBarrera = nroParaLevantarBarrera;
        }

        public synchronized void esperar(){
            this.contador++;
            while(contador < nroParaLevantarBarrera){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.notifyAll();
        }
    }

