package com.tma.comsumer;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

public class ConsumerThread implements Runnable {

	private KafkaStream<byte[], byte[]> stream;
	private int threadNumber;

	public ConsumerThread(KafkaStream<byte[], byte[]> stream, int threadNumber) {
		this.stream = stream;
		this.threadNumber = threadNumber;
	}

	@Override
	public void run() {
		ConsumerIterator<byte[], byte[]> it = stream.iterator();
		while (it.hasNext()) {
			System.out.println("Message from thread " + threadNumber + ": "
					+ new String(it.next().message()));
		}
		System.out.println("Shutting down thread: " + threadNumber);
	}

	public static void main(String[] args) {
	}
}
