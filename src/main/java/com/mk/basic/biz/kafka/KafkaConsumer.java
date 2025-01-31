package com.mk.basic.biz.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}