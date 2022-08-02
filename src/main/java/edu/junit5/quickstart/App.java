package edu.junit5.quickstart;

import javafx.application.Application;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

/**
 * Main Entry Point of FileCrypt
 *
 * @author Robin Steil
 */
public class App {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Security.insertProviderAt(new BouncyCastleProvider(), 1);
    Application.launch(JavaFXEntry.class);
  }
}
