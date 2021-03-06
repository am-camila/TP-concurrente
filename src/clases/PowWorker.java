package clases;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

public class PowWorker extends Thread implements Runnable{
    private final int dificultad;
    private final Long tiempoInicial;
    private Buffer buffer;
    private Rango rango; //rango que puede esta entre 0 y 2^32
    private String prefijo = "";

    public PowWorker(Buffer buffer, int dificultad, Long timer){
        this.buffer=buffer;
        this.dificultad = dificultad;
        this.tiempoInicial = timer;
    }

    public boolean cumpleDificultad(String result){
        boolean cumple = true;
        for(int i = 0; i<dificultad*2; i++){
            cumple = cumple && String.valueOf(result.charAt(i)).equals(Integer.toString(0));
        }
        return cumple;
    }

    public void run() {
        Rango rango = buffer.removeWorkingUnit();


        byte[] nonceValido;
        boolean noHayNonce = true;
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


        for (long i = rango.getInicio(); i < rango.getFin() && !buffer.getHayNonce(); i++) {
            try {
                outputStream.write(prefijo.getBytes());
                outputStream.write(pasarABytes((int) i));
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] digest = sha.digest(outputStream.toByteArray());
            String result = String.format("%064x", new BigInteger(1, digest));
            if (cumpleDificultad(result)) {
                nonceValido = pasarABytes((int) i);
                buffer.setHayNonce(true,i,nonceValido);
                System.out.println(this.getName() +" nonce " + i +" : " + Arrays.toString(nonceValido) + " cumple");
            }
            outputStream.reset();
        }
        Double tiempoFinal = (double) (System.currentTimeMillis() - tiempoInicial) /1000;
        System.out.println(this.getName()+" Termino en " + tiempoFinal + " segundos");

    }

    private byte[] pasarABytes(Integer i) {
       return ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(i).array();
    }
    public void temporal(Rango rango){
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