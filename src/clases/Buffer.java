package clases;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Buffer {
   List<Rango> ranges = new ArrayList<>();
    private int capacidad;
    private boolean hayNonce = false;


    public Buffer(int capacidad) {
        this.capacidad = capacidad;
    }

    synchronized public Rango removeWorkingUnit(){
        while(this.ranges.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Un thread tomó un rango");
        notifyAll();
        return this.ranges.remove(0);
    }

    synchronized public void addWorkingUnit(long primerNonce, long ultimoNonce){
        while(this.ranges.size() == capacidad){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.ranges.add(new Rango(primerNonce, ultimoNonce));
        System.out.println("Se agrego un rango");
        notifyAll();
    }

    public void setHayNonce(boolean boo, long i, byte[]nonceValido) {
        this.hayNonce = boo;
    }

    public boolean getHayNonce() {
        return hayNonce;
    }

}