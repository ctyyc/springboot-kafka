package com.mk.basic.biz.kafka;

import com.mk.basic.common.code.Topic;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@EnableKafka
@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(Topic.TEST.value, message);
    }

    public void sendMessageForMember(String message) {
        kafkaTemplate.send(Topic.MEMBER.value, message);
    }
}