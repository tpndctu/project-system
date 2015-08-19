package com.tma.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tma.dao.EntityDAO;

public class App {
	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext(
				"context.xml");
		EntityDAO entityDAO = (EntityDAO) context.getBean("entityDAO");
		System.out.println(entityDAO.countEntity());
		;
	}
}
