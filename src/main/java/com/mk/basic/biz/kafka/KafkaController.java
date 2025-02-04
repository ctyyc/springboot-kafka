package com.mk.basic.biz.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        kafkaProducerService.sendMessage(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/send/member")
    public ResponseEntity<String> sendMessageForMember(@RequestParam String message) {
        kafkaProducerService.sendMessageForMember(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/send/member/consume")
    public ResponseEntity<String> consumeMessageForMember() {
        kafkaConsumerService.consumeByPoll();
        return ResponseEntity.ok("OK");
    }
}