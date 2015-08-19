package com.tma.producer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerDemo extends Thread {
	private Producer<Integer, String> producer;
	private String topic;

	public ProducerDemo(String topic) {
		Properties properties = new Properties();
		properties.put("metadata.broker.list", "localhost:9092");
		properties.put("serializer.class", "kafka.serializer.StringEncoder");
		properties.put("request.required.acks", "1");
		producer = new Producer<>(new ProducerConfig(properties));
		this.topic = topic;
	}

	@Override
	public void run() {
		int messageNo = 1;
		while (true) {
			String messageStr = "Message " + messageNo;
			KeyedMessage<Integer, String> data = new KeyedMessage<Integer, String>(
					topic, messageStr);
			System.out.println("Sending " + messageStr);
			producer.send(data);
			messageNo++;
			if (messageNo > 100) {
				break;
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		producer.close();
	}

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new ProducerDemo("test");
		thread.start();
	}
}
