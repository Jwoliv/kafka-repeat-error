package com.example.kafkarepeaterror.sender;

import com.example.kafkarepeaterror.controller.KafkaManagerI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageSenderImpl implements KafkaMessageSender {

    private final KafkaManagerI kafkaManager;

    @Override
    public void sendMessage(String key, String value) {
        processTransaction(key, value);
    }

    private void processTransaction(String key, String value) {
        CompletableFuture.runAsync(() -> {
            kafkaManager.send1(key, value + "!!!");
            kafkaManager.send2(key, value);
        }).exceptionally(t -> {
            log.error("Error sending message", t);
            return null;
        });

    }
}