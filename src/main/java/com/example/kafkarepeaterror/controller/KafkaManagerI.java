package com.example.kafkarepeaterror.controller;

public interface KafkaManagerI {
    void send1(String key, String value);
    void send2(String key, String value);
}
