package com.tma.producer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
@EnableIntegration
@ImportResource("producer.xml")
public class ProducerDemo {
	private Log LOG = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("inputToKafka")
	MessageChannel in;

	@Bean
	@DependsOn("kafkaOutboundChannelAdapter")
	CommandLineRunner kickOff(@Qualifier("inputToKafka") MessageChannel in) {
		return args -> {
			for (int i = 0; i < 100; i++) {
				in.send(new GenericMessage<String>("fdsfas"));
				LOG.info("Sended message " + i);
				Thread.sleep(2000);
			}
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(ProducerDemo.class, args);
	}
}
