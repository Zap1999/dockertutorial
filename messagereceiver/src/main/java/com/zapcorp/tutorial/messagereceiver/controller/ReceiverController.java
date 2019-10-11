package com.zapcorp.tutorial.messagereceiver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Collections;


@RestController
public class ReceiverController {

    private RestTemplate restTemplate;

    private String URI;

    @RequestMapping("reqMsg")
    public ResponseEntity<?> requestMessage() throws Exception {
        /*return restTemplate().exchange(UriComponentsBuilder.fromHttpUrl(uri + "send").queryParam("msg", "MyRequesttosendtome!").toUriString(),
                HttpMethod.GET, null, String.class);*/
        return restTemplate.getForEntity(UriComponentsBuilder.fromHttpUrl(URI + "send").queryParam("msg", "MyRequesttosendtome!").toUriString(),
                String.class, Collections.emptyMap());
    }

    @RequestMapping("saveMsg")
    public ResponseEntity<?> saveMessage() throws Exception {
        /*return restTemplate().exchange(UriComponentsBuilder.fromHttpUrl(uri + "save").queryParam("smth", "MyRequesttosave!").toUriString(),
                HttpMethod.GET, null, String.class);*/
        return restTemplate.getForEntity(UriComponentsBuilder.fromHttpUrl(URI + "save").queryParam("smth", "MyRequesttosave!").toUriString(),
                String.class, Collections.emptyMap());
    }

    public ReceiverController(@Value("${sender.URI}") String URI, RestTemplate restTemplate) {
        this.URI = URI;
        this.restTemplate = restTemplate;
    }

}
