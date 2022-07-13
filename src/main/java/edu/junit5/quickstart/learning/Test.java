package edu.junit5.quickstart.learning;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;

public class Test {
    public static void main(String[] args) throws Exception {

        byte[] array = {1};
        //byte[] decoded = Hex.decode(array);
        byte[] encoded = Hex.encode(array);
        String string = Hex.toHexString(array);
//        EventListener eventListener = new EventListener();
//        EventHandler eventHandler = new EventHandler();
//        EventHandler<> eventHandler = new EventHandler();
        Cipher cipher = Cipher.getInstance("AES/OFB/ZeroBytePadding", new BouncyCastleProvider());
        int a = 1;
        System.out.println(a);
        System.out.println(~a);
        System.out.println(~a + 1);
    }
}
