package com.cll.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main Starter
 *
 */
public class StartMain {
	static final Logger log = LoggerFactory.getLogger(StartMain.class);
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		log.info("context DisplayName:"+ context.getDisplayName());
		log.info("The Main Class Is Running....");
	}
}
