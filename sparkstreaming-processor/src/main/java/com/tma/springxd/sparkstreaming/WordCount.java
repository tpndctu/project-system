package com.tma.springxd.sparkstreaming;

import java.util.Arrays;
import java.util.Properties;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.springframework.xd.spark.streaming.SparkConfig;
import org.springframework.xd.spark.streaming.java.Processor;

import scala.Tuple2;

@SuppressWarnings({ "serial" })
public class WordCount implements
		Processor<JavaDStream<String>, JavaPairDStream<String, Integer>> {

	@Override
	public JavaPairDStream<String, Integer> process(JavaDStream<String> input) {
		JavaDStream<String> words = input
				.flatMap(new FlatMapFunction<String, String>() {
					@Override
					public Iterable<String> call(String x) throws Exception {
						return Arrays.asList(x.split(" "));
					}
				});
		JavaPairDStream<String, Integer> wordCounts = words.mapToPair(
				new PairFunction<String, String, Integer>() {
					@Override
					public Tuple2<String, Integer> call(String s) {
						return new Tuple2<String, Integer>(s, 1);
					}
				}).reduceByKey(new Function2<Integer, Integer, Integer>() {

			@Override
			public Integer call(Integer i1, Integer i2) {
				return i1 + i2;
			}
		});
		return wordCounts;
	}

	@SparkConfig
	public Properties getSparkConfigProperties() {
		Properties pro = new Properties();
		pro.setProperty(SPARK_MASTER_URL_PROP, "local[4]");
		return pro;
	}
}
