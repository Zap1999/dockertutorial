package com.zapcorp.tutorial.messagereceiver.config;

import com.zapcorp.tutorial.messagereceiver.service.impl.ReceiverServiceImpl;
import com.zapcorp.tutorial.messagereceiver.service.impl.SignatureServiceImpl;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ReceiverConfig {


    @Bean
    public Queue testMessageQueue() {
        return new Queue("testMessageQueue");
    }

    @Bean
    public ReceiverServiceImpl receiverService() {
        return new ReceiverServiceImpl();
    }

    @Bean
    public SignatureServiceImpl signatureService() {
        return new SignatureServiceImpl();
    }

    @Bean
    public RestTemplate customRestTemplate() throws Exception {
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
