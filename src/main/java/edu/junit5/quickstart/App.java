package edu.junit5.quickstart;

import org.bouncycastle.util.encoders.Hex;

public class App {
    public static void main(String[] args) {
        System.out.println("000".getBytes());
        Model model = Model.getInstance();
        Controller controller = new Controller(model);
    }
}
