package edu.junit5.quickstart.learning;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * A simple example of AES in ECB mode.
 */
public class ECBShortExample {
    static String[] DESWeakKeys = {"0101010101010101",
            "0000000000000000",
            "FEFEFEFEFEFEFEFE",
            "FFFFFFFFFFFFFFFF",
            "E0E0E0E0F1F1F1F1",
            "E1E1E1E1F0F0F0F0",
            "1F1F1F1F0E0E0E0E",
            "1E1E1E1E0F0F0F0F"
    };

    public static void encryptDecryptDESWithWeakKey(String[] array) throws Exception {
        byte[] input = Hex.decode("a0a1a2a3a4a5a6a7a0a1a2a3a4a5a6a7");
        System.out.println("input    : " + Hex.toHexString(input));

        for (String keyAsString : array) {
            byte[] key = Hex.decode(keyAsString);
            SecretKeySpec keySpec = new SecretKeySpec(key, "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding",
                    new BouncyCastleProvider());


            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] output = cipher.doFinal(input);

            System.out.println(keyAsString + " first encryption: " + Hex.toHexString(output));

            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] sencondOutput = cipher.doFinal(output);

            System.out.println(keyAsString + " second encryption: " + Hex.toHexString(sencondOutput));


        }
    }

    public static void main(String[] args)
            throws Exception {
        encryptDecryptDESWithWeakKey(DESWeakKeys);
    }
}
