import clases.Buffer;
import clases.PowWorker;
import javafx.util.Pair;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PowWorkerTest {
    private final Buffer buffer= new Buffer(2);
    private final PowWorker pw= new PowWorker(buffer,2);
    private final List<Integer> nonces = new ArrayList<>();
    byte[] prefijo = "".getBytes();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    MessageDigest sha = MessageDigest.getInstance("SHA-256");

    public PowWorkerTest() throws NoSuchAlgorithmException {
    }

    @Test
    public void testDeConversiones() throws NoSuchAlgorithmException, IOException {

        outputStream.write(prefijo);
        outputStream.write(ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(129544).array());
        byte[] que = outputStream.toByteArray();


        byte[] sito = sha.digest(que);
        String qlo = String.format("%064x", new BigInteger(1, sito));

        assertEquals("686f6c61",prefijo);
        //   assertEquals("0028f535",nonce);
        //   assertEquals("000000000028f535",lpm);
    }

    @Test
    public void ass() throws IOException, NoSuchAlgorithmException {
        pw.temporal(new Pair<>(0, (int) Math.pow(2, 31)));
        boolean f = pw.trabajo("");
        outputStream.write(prefijo);
        /*outputStream.write(ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(129544).array());
        byte[] que = outputStream.toByteArray();
        byte[] sito = sha.digest(que);
        String qlo = String.format("%064x", new BigInteger(1, sito));

        boolean cumple = pw.cumpleDificultad(qlo);*/

        assertTrue(f);


    }


}
