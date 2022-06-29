package com.example.users.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
	
	private static final Logger log = LoggerFactory.getLogger(Listener.class);

	
	@KafkaListener(topics = "devs4j-topic", groupId = "group")
	public void listen(String message) {
		log.info("Code to post the message in the audit api {}", message);
		try {
			Thread.sleep(5000l);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
