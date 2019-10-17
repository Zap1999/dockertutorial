package com.zapcorp.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;


public class Crypto {

    public static PrivateKey extractPrivateKey(String keyStorePath, String keyStorePass, String keyPass, String keyName) throws CertificateException,
            IOException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {

        char[] keystorePassword = keyStorePass.toCharArray();
        char[] keyPassword = keyPass.toCharArray();

        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(new FileInputStream(keyStorePath), keystorePassword);

        return (PrivateKey) keystore.getKey(keyName, keyPassword);
    }

    public static Certificate extractCertificate(String certPath) throws CertificateException, FileNotFoundException {

        CertificateFactory certFactory= CertificateFactory.getInstance("X.509");

        return certFactory.generateCertificate(new FileInputStream(certPath));
    }

    public static byte[] signData(byte[] data, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        Signature sig = Signature.getInstance("SHA256WithRSA");
        sig.initSign(privateKey);
        sig.update(data);

        return sig.sign();
    }

    public static boolean verifySignedData(byte[] data, byte[] signedData, Certificate certificate) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        Signature sig = Signature.getInstance("SHA256WithRSA");
        sig.initVerify(certificate);
        sig.update(data);

        return sig.verify(signedData);
    }

}
