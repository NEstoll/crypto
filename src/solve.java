import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

public class solve {
    static String plain = "This is a top secret.";
    static String cipher = "8d20e5056a8d24d0462ce74e4904c1b513e10d1df4a2ef2ad4540fae1ca0aaf9";
    public static void main(String[] args){
        try {
            Scanner in = new Scanner(new File("words.txt"));

            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            while (in.hasNextLine()) {
                String word = in.nextLine();
                if (word.length() >= 16) {
                    continue;
                }
                while (word.length() != 16) {
                    word += ' ';
                }
                c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(word.getBytes(), "AES"), new IvParameterSpec(new byte[16]));
                byte[] e = c.doFinal(plain.getBytes());
                String out = new BigInteger(1, e).toString(16);
                if (out.equals(cipher)) {
                    System.out.println(word);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
