package com.zapcorp.tutorial.messagesender.error;

import org.apache.http.impl.execchain.RequestAbortedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({IllegalArgumentException.class, RequestAbortedException.class})
    public ResponseEntity<Object> handleExternalError(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NullPointerException.class, CertificateException.class, UnrecoverableKeyException.class, NoSuchAlgorithmException.class,
            KeyStoreException.class, SignatureException.class, InvalidKeyException.class, IOException.class})
    public ResponseEntity<Object> handleInternalError(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
