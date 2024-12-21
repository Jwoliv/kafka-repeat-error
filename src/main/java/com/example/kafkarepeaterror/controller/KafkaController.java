package com.example.kafkarepeaterror.controller;

import com.example.kafkarepeaterror.sender.KafkaMessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaMessageSender kafkaMessageSender;

    @GetMapping
    public ResponseEntity<?> send(@RequestParam String key, @RequestParam String message) {
        kafkaMessageSender.sendMessage(key, message);
        return ResponseEntity.ok().build();
    }
}
