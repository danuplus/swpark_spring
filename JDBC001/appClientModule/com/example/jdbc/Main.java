package com.example.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		MemberUI memberUI = (MemberUI)applicationContext.getBean("memberUI");
		memberUI.start();
	}

}
