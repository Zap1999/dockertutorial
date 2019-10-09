package com.zapcorp.tutorial.messagereceiver;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ReceiverController {

    private final String uri = "http://messagesender-container-1:8085/";

    @RequestMapping("reqMsg")
    public ResponseEntity<?> requestMessage() {
        return new RestTemplate().exchange(UriComponentsBuilder.fromHttpUrl(uri + "send").queryParam("msg", "MyRequesttosendtome!").toUriString(),
                HttpMethod.GET, null, String.class);
    }

    @RequestMapping("saveMsg")
    public ResponseEntity<?> saveMessage() {
        return new RestTemplate().exchange(UriComponentsBuilder.fromHttpUrl(uri + "save").queryParam("smth", "MyRequesttosave!").toUriString(),
                HttpMethod.GET, null, String.class);
    }

}
