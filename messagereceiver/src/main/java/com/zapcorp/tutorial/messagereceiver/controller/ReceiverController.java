package com.zapcorp.tutorial.messagereceiver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Collections;


@RestController
@RequestMapping("/rabbit")
public class ReceiverController {

    private final RestTemplate restTemplate;

    private final String URI;


    public ReceiverController(@Value("${sender.URI}") String URI, RestTemplate restTemplate) {
        this.URI = URI + "rabbit";
        this.restTemplate = restTemplate;
    }

    @GetMapping("/request")
    public ResponseEntity<String> requestMessage() {
        return restTemplate.getForEntity(UriComponentsBuilder.fromHttpUrl(URI + "/send").queryParam("msg", "MyRequestToSendToMe!").toUriString(),
                String.class, Collections.emptyMap());
    }

    @GetMapping("/save")
    public ResponseEntity<String> saveMessage() {
        return restTemplate.getForEntity(UriComponentsBuilder.fromHttpUrl(URI + "/save").queryParam("smth", "MyRequestToSave!").toUriString(),
                String.class, Collections.emptyMap());
    }

}
