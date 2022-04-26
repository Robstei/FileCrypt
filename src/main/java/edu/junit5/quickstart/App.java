package edu.junit5.quickstart;

import edu.junit5.quickstart.Controller;
import edu.junit5.quickstart.Model;

public class App {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        controller.encryptFile("AES", "ECB", "PKCS5Padding", "/home" +
                "/robin" +
                "/IdeaProjects/SimpleJunit5/src" +
                "/main/resources/text.txt");
        controller.decryptFile("AES", "ECB", "PKCS5Padding", "/home/robin" +
                        "/IdeaProjects/SimpleJunit5/src/main/resources/text" +
                        ".txt_alt",
                "/home/robin/IdeaProjects/SimpleJunit5/src/main/resources" +
                        "/text.txt_key");
    }
}
