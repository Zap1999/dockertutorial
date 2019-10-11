package com.zapcorp.tutorial.messagesender.controller;

import com.zapcorp.tutorial.messagesender.service.SenderService;
import com.zapcorp.tutorial.messagesender.service.impl.SenderServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@AllArgsConstructor
@RestController
public class SenderController {

    public static final Logger LOGGER = LoggerFactory.getLogger(SenderController.class);

    private SenderService senderService;


    @GetMapping("send")
    public ResponseEntity<String> send(@RequestParam(name = "msg") String msg) {
        senderService.send(msg);
        return ResponseEntity.ok("Sent:" + msg);
    }

    @GetMapping("save")
    public ResponseEntity save(@RequestParam(name = "smth") String smth) {
        LOGGER.info("Got to save: " + smth);
        return ResponseEntity.accepted().build();
    }

}
