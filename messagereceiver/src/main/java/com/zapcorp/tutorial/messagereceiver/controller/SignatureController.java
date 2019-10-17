package com.zapcorp.tutorial.messagereceiver.controller;

import com.zapcorp.tutorial.messagereceiver.service.SignatureService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Collections;


@RestController
@RequestMapping("/sig")
public class SignatureController {

    private final RestTemplate restTemplate;

    private final String URI;

    private final SignatureService signatureService;


    public SignatureController(@Value("${sender.URI}") String URI, RestTemplate restTemplate, SignatureService signatureService) {
        this.URI = URI + "sig";
        this.restTemplate = restTemplate;
        this.signatureService = signatureService;
    }

    @GetMapping("/last")
    public ResponseEntity<String> requestLastMessage() throws IOException, CertificateException, InvalidKeyException, NoSuchAlgorithmException,
            SignatureException {
        System.out.println(URI + "/last");
        String msg = restTemplate.getForEntity(UriComponentsBuilder.fromHttpUrl(URI + "/last").toUriString(),
                String.class, Collections.emptyMap()).getBody();
        String msgSig = restTemplate.getForEntity(UriComponentsBuilder.fromHttpUrl(URI + "/sig").toUriString(),
                String.class, Collections.emptyMap()).getBody();

        if (!signatureService.certExists()) {
            ByteArrayResource certResp = restTemplate.getForEntity(UriComponentsBuilder.fromHttpUrl(URI + "/cert").toUriString(),
                    ByteArrayResource.class, Collections.emptyMap()).getBody();

            signatureService.setCertFromResource(certResp);
        }

        return ResponseEntity.ok(String.format("Message: '%s'\nVerified: %b", new String(Base64.getDecoder().decode(msg)), signatureService.verify(msg, msgSig)));
    }

}
