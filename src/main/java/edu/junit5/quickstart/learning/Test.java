package edu.junit5.quickstart.learning;

import javafx.beans.property.SimpleStringProperty;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v1CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v1CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;

public class Test {

  ;

  public static void main(String[] args) throws Exception {
    SimpleStringProperty s1 = new SimpleStringProperty("a");
    SimpleStringProperty s2 = new SimpleStringProperty("b");
    s1.bind(s2);
    System.out.println(s1.getValue());
    System.out.println(s2.getValue());

    Security.insertProviderAt(new BouncyCastleProvider(), 1);

    for (Provider provider : Security.getProviders()) {
      if (provider.getName().equals("BC")) {
        for (Provider.Service service : provider.getServices()) {
          if (service.getAlgorithm().contains(
                  "PBEWithSHA256And128BitAES-CBC-BC".toUpperCase()))
            System.out.println(service.getAlgorithm());
        }
      }
    }

    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "BC");
    SecretKey secretKey = keyGenerator.generateKey();
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA",
                                                                     "BC");
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    PublicKey publicKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();

    X500NameBuilder x500NameBld = new X500NameBuilder(BCStyle.INSTANCE)
            .addRDN(BCStyle.C, "DE")
            .addRDN(BCStyle.ST, "NRW")
            .addRDN(BCStyle.L, "Essen")
            .addRDN(BCStyle.O, "HSD")
            .addRDN(BCStyle.CN, "Self Signed Demo Certificate");
    X500Name name = x500NameBld.build();

    X509v1CertificateBuilder certBldr = new JcaX509v1CertificateBuilder(
            name,
            BigInteger.valueOf(111111111),
            new Date(10 * 60 * 60 * 24 * 20),
            new Date(new Date().getTime() + (10 * 60 * 60 * 24 * 30)),
            name,
            keyPair.getPublic());

    ContentSigner signer = new JcaContentSignerBuilder("SHA256withDSA")
            .setProvider("BC").build(keyPair.getPrivate());

    X509CertificateHolder x509CertificateHolder = certBldr.build(signer);

    X509Certificate x509Certificate =
            new JcaX509CertificateConverter().setProvider(
                    "BC").getCertificate(x509CertificateHolder);
    Certificate[] certificates = {x509Certificate};

    KeyStore keyStore = KeyStore.getInstance("BCFKS", "BC");
    keyStore.load(null, null);
    keyStore.setKeyEntry("myKey", secretKey, "hallo2".toCharArray(), null);
    keyStore.setKeyEntry("myKey3", privateKey, "hallo2".toCharArray(),
                         certificates);
    try (FileOutputStream fos = new FileOutputStream(
            "/home/robin/Desktop/keystore.fks");
    ) {
      keyStore.store(fos, "hallo".toCharArray());
    }


    KeyStore keystore2 = KeyStore.getInstance("BCFKS", "BC");
    try (FileInputStream fis = new FileInputStream(
            "/home/robin/Desktop/keystore.fks")) {

      keystore2.load(fis, "hallo".toCharArray());
      Key key = keystore2.getKey("myKey", "hallo2".toCharArray());
      Key key2 = keystore2.getKey("myKey3", "hallo2".toCharArray());

      SecretKey secretKey1 = (SecretKey) key;
      PrivateKey privateKey1 = (PrivateKey) key2;
    }
  }
}
