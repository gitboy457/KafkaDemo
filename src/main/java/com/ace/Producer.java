package com.ace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@ComponentScan
@Service
public class Producer {
private static final Logger logger = LoggerFactory.getLogger(Producer.class);
private static final String TOPIC = "Hello-Kafka";
@Autowired
private KafkaTemplate<String,String> kafkaTemplate;
public void sendMessage(String message){
logger.info(String.format("$$ -> Producing message --> %s",message));
this.kafkaTemplate.send(TOPIC,message);
}
public KafkaTemplate<String, String> getKafkaTemplate() {
	return kafkaTemplate;
}
public void setKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate) {
	this.kafkaTemplate = kafkaTemplate;
}



}