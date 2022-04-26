package edu.junit5.quickstart.learning;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * 2 * A simple application to check for unrestricted policy files.
 * 3
 */
public class PolicyFileCheck {
    public static void main(String[] args)
            throws NoSuchAlgorithmException {
        try {
            Cipher cipher = Cipher.getInstance("Blowfish/ECB/NoPadding", "BC");

            cipher.init(Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(new byte[32], "Blowfish"));

            System.out.print("true");
        } catch (NoSuchAlgorithmException e) {
            throw e;
        } catch (Exception e) {
            System.out.print("false");
        }
    }
}
