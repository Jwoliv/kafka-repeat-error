package com.example.kafkarepeaterror.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageSenderImpl implements KafkaMessageSender {

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String key, String value) {
        kafkaTemplate.executeInTransaction(transactional -> {
            processTransaction(key, value);
            log.info("transactional message [{}]", transactional);
            return true;
        });
    }

    private void processTransaction(String key, String value) {
        kafkaTemplate.send(topic, key, value);
    }
}