package com.tma.consumer;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

public class App {
	private static ClassPathXmlApplicationContext context;
	private static Scanner scanner;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		context = new ClassPathXmlApplicationContext("producer.xml");
		MessageChannel channel = (MessageChannel) context
				.getBean("inputToKafka");
		while (true) {
			System.out.print("message :");
			String msg = scanner.next();
			channel.send(new GenericMessage<String>(msg));
			System.out.println("thanh cong");
			if (msg.equals("e")) {
				break;
			}
		}
	}
}
