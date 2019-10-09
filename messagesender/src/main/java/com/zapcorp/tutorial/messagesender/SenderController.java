package com.zapcorp.tutorial.messagesender;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SenderController {
    private final Sender sender;

    public SenderController(Sender sender) {
        this.sender = sender;
    }

    @RequestMapping("send")
    public ResponseEntity<String> send(@RequestParam(name = "msg") String msg) {
        sender.send(msg);
        return ResponseEntity.ok("Sent:" + msg);
    }

    @RequestMapping("save")
    public ResponseEntity save(@RequestParam(name = "smth") String smth) {
        System.out.println("Got to save: " + smth);
        return ResponseEntity.accepted().build();
    }

}
