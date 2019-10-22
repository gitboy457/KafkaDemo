package com.ace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@ComponentScan
@Service
public class Consumer {
private final Logger logger = LoggerFactory.getLogger(Consumer.class);
@KafkaListener(topics = "Hello-Kafka", groupId = "group_id")
public void consume(String message){
logger.info(String.format("$$ -> Consumed Message -> %s",message));
}
}

