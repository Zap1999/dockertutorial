package com.zapcorp.tutorial.messagereceiver.controller;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.SSLContext;
import java.net.URL;
import java.util.Collections;


@RestController
public class ReceiverController {

    @Value("${trust.store}")
    private URL trustStore;
    @Value("${trust.store.password}")
    private String trustStorePassword;

    private static final String URI = "https://sender:8080/";

    @RequestMapping("reqMsg")
    public ResponseEntity<?> requestMessage() throws Exception {
        /*return restTemplate().exchange(UriComponentsBuilder.fromHttpUrl(uri + "send").queryParam("msg", "MyRequesttosendtome!").toUriString(),
                HttpMethod.GET, null, String.class);*/
        return restTemplate().getForEntity(UriComponentsBuilder.fromHttpUrl(URI + "send").queryParam("msg", "MyRequesttosendtome!").toUriString(),
                String.class, Collections.emptyMap());
    }

    @RequestMapping("saveMsg")
    public ResponseEntity<?> saveMessage() throws Exception {
        /*return restTemplate().exchange(UriComponentsBuilder.fromHttpUrl(uri + "save").queryParam("smth", "MyRequesttosave!").toUriString(),
                HttpMethod.GET, null, String.class);*/
        return restTemplate().getForEntity(UriComponentsBuilder.fromHttpUrl(URI + "save").queryParam("smth", "MyRequesttosave!").toUriString(),
                String.class, Collections.emptyMap());
    }

    private RestTemplate restTemplate() throws Exception {
        System.out.println(trustStore);
        System.out.println(trustStorePassword);
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(trustStore, trustStorePassword.toCharArray())
                .build();
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
        HttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(socketFactory)
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
    }

}
