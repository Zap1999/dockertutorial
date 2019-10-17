package com.zapcorp.tutorial.messagesender.service.impl;

import com.zapcorp.tutorial.messagesender.service.SenderService;
import com.zapcorp.tutorial.messagesender.service.SignatureService;
import com.zapcorp.util.Crypto;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.apache.http.impl.execchain.RequestAbortedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;


@Service
public class SignatureServiceImpl implements SignatureService {


    private String keyStorePath;


    private String keyStorePass;


    private String keyPass;


    private String keyName;


    private String certPath;

    private SenderService senderService;

    public SignatureServiceImpl(@Value("${server.ssl.key-store}") String keyStorePath,
                                @Value("${server.ssl.key-store-password}") String keyStorePass,
                                @Value("${server.ssl.private-key-pass}") String keyPass,
                                @Value("${server.ssl.key-alias}") String keyName,
                                @Value("${server.ssl.cert}") String certPath, SenderService senderService) {

        this.keyStorePath = keyStorePath;
        this.keyStorePass = keyStorePass;
        this.keyPass = keyPass;
        this.keyName = keyName;
        this.certPath =certPath;
        this.senderService = senderService;
    }

    @Override
    public String getLastMessage() throws RequestAbortedException {

       try {
           return Base64.getEncoder().encodeToString(senderService.getLastMessage().getBytes());
       } catch (NullPointerException ex) {
           throw new RequestAbortedException("No last message yet.");
       }

    }

    @Override
    public String getLastMessageSigned() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException,
            KeyStoreException, IOException, SignatureException, InvalidKeyException {

        PrivateKey privateKey = Crypto.extractPrivateKey(keyStorePath, keyStorePass, keyPass, keyName);

        try {
            byte[] sig = Crypto.signData(senderService.getLastMessage().getBytes(), privateKey);
            return Base64.getEncoder().encodeToString(sig);
        } catch (NullPointerException ex) {
            throw new RequestAbortedException("No last message yet.");
        }
    }

    @Override
    public ByteArrayResource getCertAsByteArrayResource() throws IOException {

        File certFile = new File(certPath);

        return new ByteArrayResource(Files.readAllBytes(Paths.get(certFile.getAbsolutePath())));
    }

}
