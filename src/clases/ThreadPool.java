package clases;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
    private List<PowWorker> workers = new ArrayList<>();


    public void createWorkers(int cantWorkers,Buffer buffer, int dificultad) {
        int cant = cantWorkers;
        while (cant >0){
            PowWorker worker = new PowWorker(buffer,dificultad);
            workers.add(worker);
            worker.start();
            cant--;
        }

    }

  /*   public void stopWorkers(){
        for (int i = 0; i < workers.size(); i++) {
        workers.get(i).stop();
        }
    }
*/
}












/*
public class ThreadPool {
    private List<Worker> workers = new ArrayList();
    private Buffer<Runnable> runnableBuffer;
    public ThreadPool(int cantWorkers,int capacidadDelBuffer) {
        runnableBuffer = new Buffer<>(capacidadDelBuffer);
        this.crearWorkers(cantWorkers);
        this.startWorkers();
    }

    private void startWorkers() {
        workers.forEach(w -> w.start());
    }

    private void crearWorkers(int cantWorkers) {
        for (int i = 0; i < cantWorkers; i++) {
            workers.add(new Worker(runnableBuffer));
        }
    }
    synchronized public void launch(Runnable task){
        runnableBuffer.enqueue(task);
    }

    synchronized public void stop(){
        for (int i = 0; i < workers.size(); i++) {
            runnableBuffer.enqueue(new PoisonPill());
        }
    }

}
*/