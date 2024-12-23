package com.example.kafkarepeaterror.kafka_manager;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaManagerImpl implements KafkaManager {
    @Value("${spring.kafka.template.topic-1}")
    private String topic1;
    @Value("${spring.kafka.template.topic-2}")
    private String topic2;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, String> kafkaTemplateInteger;

    @Override
    public void send1(String key, String message) {
        kafkaTemplate.send(topic1, key, message);
    }

    @Override
    public void send2(String key, String message) {
        kafkaTemplateInteger.send(topic2, key, message);
    }
}
