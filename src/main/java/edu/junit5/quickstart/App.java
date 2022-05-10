package edu.junit5.quickstart;

public class App {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
    }
}
