package com.zapcorp.tutorial.messagesender.service;

import org.apache.http.impl.execchain.RequestAbortedException;
import org.springframework.core.io.ByteArrayResource;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;


public interface SignatureService {


    String getLastMessage() throws RequestAbortedException;

    String getLastMessageSigned() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException,
            KeyStoreException, IOException, SignatureException, InvalidKeyException;

    ByteArrayResource getCertAsByteArrayResource() throws CertificateException, IOException;

}
