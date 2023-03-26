package com.performance.controller;

import com.performance.service.KafkaConsumerService;
import com.performance.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: performance-test-core
 * @description: This part provide a way to consume the Kafka message
 * @author: yfliu
 * @create: 2023-03-25 19:58
 **/
@RestController
@RequestMapping("/Kafka")
public class KafkaConsumerControl {

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @PostMapping("/consume")
    public ResponseMsg consume(@RequestParam Integer messageNum) {
        return kafkaConsumerService.consume(messageNum);
    }
}
