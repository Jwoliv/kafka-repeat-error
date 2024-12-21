package com.example.kafkarepeaterror.sender;

public interface KafkaMessageSender {
    void sendMessage(String key, String message);
}
