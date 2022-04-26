package edu.junit5.quickstart;

public class Util {
    static void printByteArrayAsTextToConsole(byte[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.write(array[i]);
            System.out.println(array[i]);

        }
        System.out.flush();
    }
}
