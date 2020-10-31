package clases;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;


public class Buffer {
   List<Pair> ranges = new ArrayList<>();
    private int capacidad;
    private boolean hayNonce = false;


    public Buffer(int capacidad) {
        this.capacidad = capacidad;
    }

    synchronized public Pair removeWorkingUnit(){
        while(this.ranges.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hay espacio libre en el buffer");
        notifyAll();
        return this.ranges.remove(0);
    }

    synchronized public void addWorkingUnit(int primerNonce, int ultimoNonce){
        while(this.ranges.size() == capacidad){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.ranges.add(new Pair(primerNonce,ultimoNonce));
        System.out.println("Se agrego un rango");
        notifyAll();
    }

    public void setHayNonce(boolean boo) {
        this.hayNonce = boo;
    }

    public boolean getHayNonce() {
        return hayNonce;
    }

}