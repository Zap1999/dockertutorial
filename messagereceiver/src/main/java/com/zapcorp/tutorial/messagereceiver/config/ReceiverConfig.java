package com.zapcorp.tutorial.messagereceiver.config;

import com.zapcorp.tutorial.messagereceiver.service.Receiver;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.net.URL;

@Configuration
public class ReceiverConfig {

    @Value("${trust.store}")
    private URL trustStore;
    @Value("${trust.store.password}")
    private String trustStorePassword;

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Bean
    public Receiver receiver() {
        return new Receiver();
    }

    @Bean
    public RestTemplate restTemplate() throws Exception {
        System.out.println(trustStore);
        System.out.println(trustStorePassword);
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(trustStore, trustStorePassword.toCharArray())
                .build();
        // SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(),
                NoopHostnameVerifier.INSTANCE);
        HttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(socketFactory)
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
    }

}