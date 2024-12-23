package com.example.kafkarepeaterror.sender;

import com.example.kafkarepeaterror.kafka_manager.KafkaManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageSenderImpl implements KafkaMessageSender {

    private final KafkaManager kafkaManager;

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