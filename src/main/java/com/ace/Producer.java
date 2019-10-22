package com.ace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@ComponentScan
@Service
public class Producer {
	
private static final Logger logger = LoggerFactory.getLogger(Producer.class);
private static final String TOPIC = "Hello-Kafka";
@Autowired
private KafkaTemplate<String,Person> kafkaTemplate;


	/*
	 * public void sendMessage(String message){
	 * logger.info(String.format("$$ -> Producing message --> %s",message));
	 * //this.kafkaTemplate.send(TOPIC,message); ListenableFuture<SendResult<String,
	 * String>> future = this.kafkaTemplate.send(TOPIC, message);
	 * 
	 * future.addCallback(new ListenableFutureCallback<SendResult<String, String>>()
	 * {
	 * 
	 * @Override public void onSuccess(SendResult<String, String> result) {
	 * System.out.println("Sent message=[" + message + "] with offset=[" +
	 * result.getRecordMetadata().offset() + "]"); }
	 * 
	 * @Override public void onFailure(Throwable ex) {
	 * System.out.println("Unable to send message=[" + message + "] due to : " +
	 * ex.getMessage()); } }); }
	 */

public void sendPerson(Person person){
logger.info(String.format("$$ -> Producing message --> %s",person));
//this.kafkaTemplate.send(TOPIC,message);
ListenableFuture<SendResult<String, Person>> future = this.kafkaTemplate.send(TOPIC, person);

future.addCallback( new ListenableFutureCallback<SendResult<String, Person>>() {

  @Override
  public void onSuccess(SendResult<String, Person> result) {
      System.out.println("Sent message=[" + person + 
        "] with offset=[" + result.getRecordMetadata().offset() + "]");
  }
  @Override
  public void onFailure(Throwable ex) {
      System.out.println("Unable to send message=["
        + person + "] due to : " + ex.getMessage());
  }
});
}
public KafkaTemplate<String, Person> getKafkaTemplate() {
	return kafkaTemplate;
}
public void setKafkaTemplate(KafkaTemplate<String, Person> kafkaTemplate) {
	this.kafkaTemplate = kafkaTemplate;
}



}