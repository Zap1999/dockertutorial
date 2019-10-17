package com.zapcorp.tutorial.messagesender.service;


public interface SenderService {


    void send(String message);

    String getLastMessage();

}
