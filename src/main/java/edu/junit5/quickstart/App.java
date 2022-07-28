package edu.junit5.quickstart;

import javafx.application.Application;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public class App {

  public static void main(String[] args) {
    Security.insertProviderAt(new BouncyCastleProvider(), 1);
    Application.launch(JavaFXEntry.class);
  }
}
