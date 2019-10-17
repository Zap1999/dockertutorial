package com.zapcorp.tutorial.messagesender.controller;

import com.zapcorp.tutorial.messagesender.service.SignatureService;
import lombok.AllArgsConstructor;
import org.apache.http.impl.execchain.RequestAbortedException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;


@AllArgsConstructor
@RestController
@RequestMapping("/sig")
public class SignatureController {

    private final SignatureService signatureService;


    @GetMapping("/last")
    public ResponseEntity lastMessage() throws RequestAbortedException {

        String msg = signatureService.getLastMessage();

        return ResponseEntity.ok(msg);
    }

    @GetMapping("/sig")
    public ResponseEntity lastMessageSigned() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException,
            KeyStoreException, SignatureException, InvalidKeyException, IOException {

        String sig = signatureService.getLastMessageSigned();

        return ResponseEntity.ok(sig);
    }

    @GetMapping("cert")
    public ResponseEntity<Resource> cert() throws IOException, CertificateException {

        ByteArrayResource resource = signatureService.getCertAsByteArrayResource();

        return ResponseEntity.ok()
                .headers(null)
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

}
