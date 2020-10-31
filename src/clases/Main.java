package clases;

import java.util.Scanner;

public class Main {
    private static int dificultad;
    static Scanner scanner = new Scanner(System.in);
    private static int threads;
    private static int tamanioRango;
    private static Buffer buffer;
    private static ThreadPool threadPool;

    public static void main(String[] args) {

        // la cadena inicial puede ser la vacia. si se pone enter solo sin poner nada calcula el nonce a secas(?
        System.out.println("Ingrese una dificultad(entero positivo): ");
        dificultad = scanner.nextInt();
        System.out.println("Ingresa una cantidad de threads(entero positivo): ");
        threads = scanner.nextInt();
        tamanioRango = (int) (Math.pow(2, 32) / threads);
        buffer = new Buffer(2);
        threadPool = new ThreadPool();
        Productor productor = new Productor(buffer, tamanioRango);

        threadPool.createWorkers(threads, buffer, dificultad);
        productor.start();

    }


}


