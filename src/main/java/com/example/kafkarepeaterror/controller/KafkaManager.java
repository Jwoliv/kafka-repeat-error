package com.example.kafkarepeaterror.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaManager implements KafkaManagerI {
    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, String> kafkaTemplateInteger;

    @Override
    public void send1(String key, String message) {
        kafkaTemplate.send(topic, key, message);
    }

    @Override
    public void send2(String key, String message) {
        kafkaTemplateInteger.send(topic, key, message);
    }
}
