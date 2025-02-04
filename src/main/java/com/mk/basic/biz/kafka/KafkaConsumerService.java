package com.mk.basic.biz.kafka;

import com.mk.basic.common.code.ConsumerGroup;
import com.mk.basic.common.code.Topic;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Slf4j
@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "test", groupId = "test")
    public void consumeByListener(String message) {
        log.info("=== Consumed message: {}", message);
    }

    public void consumeByPoll() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, ConsumerGroup.MEMBER.value);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList(Topic.MEMBER.value));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(2000)); // 2초 대기
                for (ConsumerRecord<String, String> record : records) {
                    log.info("=== Consumed message: {}", record.value());
                }
            }
        } catch (Exception e) {
            log.error("=== Consumer error : {}", e.getMessage());
            throw e;
        }
    }
}