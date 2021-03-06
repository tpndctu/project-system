package com.tma.comsumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class SimpleConsumer {
	private ConsumerConnector consumer;
	private String topic;

	public SimpleConsumer(String topic) {
		Properties props = new Properties();
		props.put("zookeeper.connect", "localhost:2181");
		props.put("group.id", "testgroup");
		props.put("zookeeper.session.timeout.ms", "500");
		props.put("zookeeper.sync.time.ms", "250");
		props.put("auto.commit.interval.ms", "1000");
		consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(
				props));
		this.topic = topic;
	}

	public void testConsumer() {
		Map<String, Integer> topicCount = new HashMap<>();
		topicCount.put(topic, 1);

		Map<String, List<KafkaStream<byte[], byte[]>>> consumerStreams = consumer
				.createMessageStreams(topicCount);
		List<KafkaStream<byte[], byte[]>> streams = consumerStreams.get(topic);
		for (final KafkaStream stream : streams) {
			ConsumerIterator<byte[], byte[]> it = stream.iterator();
			while (it.hasNext()) {
				System.out.println("Message from Single Topic: "
						+ new String(it.next().message()));
			}
		}
		if (consumer != null) {
			consumer.shutdown();
		}
	}

	public static void main(String[] args) {
		SimpleConsumer demo = new SimpleConsumer("test");
		demo.testConsumer();
	}
}
