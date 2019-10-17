package com.zapcorp.tutorial.messagereceiver.service.impl;

import com.zapcorp.tutorial.messagereceiver.service.SignatureService;
import com.zapcorp.util.Crypto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Base64;


@Service
public class SignatureServiceImpl implements SignatureService {

    private Certificate certificate = null;


    @Override
    public void setCertFromResource(ByteArrayResource resource) throws IOException, CertificateException {

        BufferedOutputStream bof = new BufferedOutputStream(new FileOutputStream("certificate"));
        bof.write(resource.getByteArray());
        bof.flush();

        certificate = Crypto.extractCertificate("certificate");
    }

    @Override
    public boolean certExists() {
        return certificate != null;
    }

    @Override
    public boolean verify(String msg, String msgSig) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        return Crypto.verifySignedData(Base64.getDecoder().decode(msg), Base64.getDecoder().decode(msgSig), certificate);
    }

}
