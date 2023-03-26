package com.performance.service;

import com.performance.utils.ResponseMsg;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;


/**
 * @program: performance-test-core
 * @description: Kafka Consumer
 * @author: yfliu
 * @create: 2023-03-25 20:00
 **/
@Service
public class KafkaConsumerService {

    static final String TOPIC = "ccs-cw2-test";
    static final String KAFKA_IP = "20.117.182.16";
    static final String KAFKA_PORT = "9092";

    public ResponseMsg consume(Integer totalMessages) {

        Properties props = new Properties();
        // kafka address
        props.put("bootstrap.servers", KAFKA_IP+":"+KAFKA_PORT);
        // set group id
        props.put("group.id", "bigdata");
        // set enable auto commit
        props.put("enable.auto.commit", "true");
        // set auto commit interval
        props.put("auto.commit.interval.ms", "1000");
        // set earliest
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Collections.singletonList(TOPIC));
        boolean allMessagesConsumed = false;
        int messagesConsumed = 0;
        long startTime = 0;
        boolean flag = true;
        try{
        while (!allMessagesConsumed) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                if(flag) {
                    startTime = System.currentTimeMillis();
                    flag = false;
                }
                System.out.printf("partition = %d,offset = %d, key = %s, value = %s%n", record.partition(),
                        record.offset(), record.key(), record.value());
                int partition = record.partition();
                long offset = record.offset();
                String key = record.key();
                String value = record.value();
                messagesConsumed++;
                System.out.printf("partition = %d,offset = %d, key = %s, value = %s%n", partition, offset, key, value);
                if (messagesConsumed >= totalMessages) {
                    allMessagesConsumed = true;

                    consumer.close();
                    System.out.println("All messages consumed");
                }
            }
            consumer.commitAsync();
        }}catch (Exception e){
        }
        long endTime = System.currentTimeMillis();
        return ResponseMsg.ok("Total time taken to consume " + totalMessages + " messages is " + (endTime - startTime) + "ms");

    }
}
