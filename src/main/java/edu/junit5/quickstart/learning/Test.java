package edu.junit5.quickstart.learning;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws Exception {
        byte[] keyBytes = Hex.decode("000102030405060708090a0b0c0d0e0f");
        SecretKey key = new SecretKeySpec(keyBytes, "AES");
        System.out.println(Arrays.toString(keyBytes));
        System.out.println(Arrays.toString(key.getEncoded()));

        SecureRandom random = SecureRandom.getInstance("DEFAULT",
                new BouncyCastleProvider());
        byte[] bytes = new byte[5];
        random.nextBytes(bytes);
        System.out.println(Arrays.toString(bytes));


    }
}
