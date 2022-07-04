package xyz.mynt.singson.productms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductLogServiceImpl implements ProductLogService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Override
	public void sendLog(String message) {
		kafkaTemplate.send("ms-topic", message);
		
	}

}
