package com.zapcorp.tutorial.messagereceiver.service;

import org.springframework.core.io.ByteArrayResource;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;


public interface SignatureService {


    void setCertFromResource(ByteArrayResource resource) throws IOException, CertificateException;

    boolean verify(String msg, String msgSig) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException;

    boolean certExists();

}
