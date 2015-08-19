package com.tma;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import scala.Tuple2;

import com.google.common.collect.Lists;

/*
 * run app with spark streaming :
 * 	mvn clean package
 * 	spark-submit --class "com.tma.JavaKafkaWordCount" target\spark-streaming-0.0.1-SNAPSHOT-jar-with-dependencies.jar
 */
public class JavaKafkaWordCount {
	private static final Pattern SPACE = Pattern.compile(" ");

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("JavaKafkaWordCount");
		JavaStreamingContext jssc = new JavaStreamingContext(conf,
				new Duration(2000));
		int numThread = 1;
		String zkQuorum = "localhost:2181";
		String group = "testgroup";
		Map<String, Integer> topicMap = new HashMap<String, Integer>();
		topicMap.put("test", numThread);
		JavaPairReceiverInputDStream<String, String> messages = KafkaUtils
				.createStream(jssc, zkQuorum, group, topicMap);
		JavaDStream<String> lines = messages
				.map(new Function<Tuple2<String, String>, String>() {
					private static final long serialVersionUID = 1L;

					@Override
					public String call(Tuple2<String, String> tuple2) {
						return tuple2._2();
					}
				});

		JavaDStream<String> words = lines
				.flatMap(new FlatMapFunction<String, String>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Iterable<String> call(String x) {
						return Lists.newArrayList(SPACE.split(x));
					}
				});

		JavaPairDStream<String, Integer> wordCounts = words.mapToPair(
				new PairFunction<String, String, Integer>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Tuple2<String, Integer> call(String s) {
						return new Tuple2<String, Integer>(s, 1);
					}
				}).reduceByKey(new Function2<Integer, Integer, Integer>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Integer call(Integer i1, Integer i2) {
				return i1 + i2;
			}
		});

		wordCounts.print();
		jssc.start();
		jssc.awaitTermination();
	}
}
