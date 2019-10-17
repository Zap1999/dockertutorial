package com.zapcorp.tutorial.messagesender.controller;

import com.zapcorp.tutorial.messagesender.service.SenderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/rabbit")
public class SenderController {

    private final SenderService senderService;


    @GetMapping("/send")
    public ResponseEntity<String> send(@RequestParam(name = "msg") String msg) {

        senderService.send(msg);

        return ResponseEntity.ok("Sent:" + msg);
    }

    @GetMapping("/save")
    public ResponseEntity save(@RequestParam(name = "smth") String smth) {

        log.info("Got to save: " + smth);

        return ResponseEntity.accepted().build();
    }

}
