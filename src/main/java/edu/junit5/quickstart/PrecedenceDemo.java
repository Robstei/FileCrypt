package edu.junit5.quickstart;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.Security;

public class PrecedenceDemo {
    /**
     2 * A simple application demonstrating the effect of provider precedence on
     3 * what is returned by a JVM.
     4 */

        public static void main(String[] args)
        throws Exception
         {
            // adds BC to the end of the precedence list
             Security.addProvider(new BouncyCastleProvider());

             System.out.println(MessageDigest.getInstance("SHA1")
                    .getProvider().getName());

             System.out.println(MessageDigest.getInstance("SHA1", "BC")
                     .getProvider().getName());
             }
         }

