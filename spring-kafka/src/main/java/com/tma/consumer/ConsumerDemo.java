package com.tma.consumer;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.SourcePollingChannelAdapterSpec;
import org.springframework.integration.dsl.kafka.Kafka;
import org.springframework.integration.dsl.kafka.KafkaHighLevelConsumerMessageSourceSpec;
import org.springframework.integration.dsl.support.Consumer;
import org.springframework.integration.kafka.support.ZookeeperConnect;
import org.springframework.stereotype.Component;

@EnableIntegration
@SpringBootApplication
public class ConsumerDemo {
	public static final String TOPIC = "test";

	@Component
	public static class KafkaConfig {

		@Value("${kafka.topic:" + TOPIC + "}")
		private String topic;
		@Value("${kafka.address:localhost:9092}")
		private String brokerAddress;
		@Value("${zookeeper.address:localhost:2181}")
		private String zookeeperAddress;

		public KafkaConfig() {
		}

		public KafkaConfig(String topic, String brokerAddress,
				String zookeeperAddress) {
			this.topic = topic;
			this.brokerAddress = brokerAddress;
			this.zookeeperAddress = zookeeperAddress;
		}

		public String getTopic() {
			return topic;
		}

		public void setTopic(String topic) {
			this.topic = topic;
		}

		public String getBrokerAddress() {
			return brokerAddress;
		}

		public void setBrokerAddress(String brokerAddress) {
			this.brokerAddress = brokerAddress;
		}

		public String getZookeeperAddress() {
			return zookeeperAddress;
		}

		public void setZookeeperAddress(String zookeeperAddress) {
			this.zookeeperAddress = zookeeperAddress;
		}

	}

	@Configuration
	public static class ConsumerConfig {
		@Autowired
		private KafkaConfig kafkaConfig;
		private Log log = LogFactory.getLog(getClass());

		@Bean
		IntegrationFlow consumer() {
			log.info("starting consumer..");
			KafkaHighLevelConsumerMessageSourceSpec messageSourceSpec = Kafka
					.inboundChannelAdapter(new ZookeeperConnect(
							this.kafkaConfig.getZookeeperAddress()));

			messageSourceSpec.consumerProperties(props -> props.put(
					"auto.offset.reset", "smallest").put(
					"auto.commit.interval.ms", "100"));

			messageSourceSpec.addConsumer(
					"myGroup",
					metadata -> metadata
							.consumerTimeout(100)
							.topicStreamMap(
									m -> m.put(this.kafkaConfig.getTopic(), 1))
							.maxMessages(10).valueDecoder(String::new));

			Consumer<SourcePollingChannelAdapterSpec> endpointConfigurer = e -> e
					.poller(p -> p.fixedDelay(100));

			return IntegrationFlows
					.from(messageSourceSpec, endpointConfigurer)
					.<Map<String, List<String>>> handle(
							(payload, headers) -> {
								payload.entrySet().forEach(
										e -> log.info(e.getKey() + '='
												+ e.getValue()));
								return null;
							}).get();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerDemo.class, args);
	}
}
