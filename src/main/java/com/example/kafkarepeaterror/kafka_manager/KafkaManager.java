package com.example.kafkarepeaterror.kafka_manager;

public interface KafkaManager {
    void send1(String key, String value);
    void send2(String key, String value);
}
