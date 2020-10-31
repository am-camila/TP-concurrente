package clases;

import javafx.util.Pair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PowWorker extends Thread{
    private final int dificultad;
    private Buffer buffer;
    private Pair<Integer,Integer> rango; //rango que puede esta entre 0 y 2^32

    public PowWorker(Buffer buffer, int dificultad){
        this.buffer=buffer;
        this.dificultad = dificultad;
    }

    public boolean cumpleDificultad(String result){
        boolean cumple = true;
        for(int i = 0; i<dificultad*2; i++){
            cumple = cumple && String.valueOf(result.charAt(i)).equals(Integer.toString(0));
        }
        return cumple;
    }

    public synchronized boolean trabajo(String pref) throws IOException, NoSuchAlgorithmException {
        Pair<Integer,Integer> rango = buffer.removeWorkingUnit();


        byte[] nonceValido;
        boolean noHayNonce = true;
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


        for (int i = rango.getKey(); i < rango.getValue() && !buffer.getHayNonce(); i++) {
            outputStream.write(pref.getBytes());
            outputStream.write(pasarABytes(i));
            byte[] digest = sha.digest(outputStream.toByteArray());
            String result = String.format("%064x", new BigInteger(1, digest));
            if (cumpleDificultad(result)) {
                buffer.setHayNonce(true);
                nonceValido = pasarABytes(i);
                System.out.println("nonce " + i +" : " + Arrays.toString(nonceValido) + " cumple");
            }
            outputStream.reset();

        }

        return noHayNonce;
    }

    private byte[] pasarABytes(Integer i) {
       return ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(i).array();
    }
    public void temporal(Pair<Integer,Integer> rango){
        this.rango = rango;
    }

}





/*   private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
      while (true) {
          buffer.pop();
      }
    }*/