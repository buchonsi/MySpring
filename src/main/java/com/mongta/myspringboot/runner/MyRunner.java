package com.mongta.myspringboot.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.mongta.myspringboot.property.MongtaProperties;


@Component
public class MyRunner implements ApplicationRunner{

	//Logger 생성
	Logger logger = LoggerFactory.getLogger(MyRunner.class);
	
	@Autowired
	MongtaProperties mongtaProp;
	@Autowired
	private String hello;
	
//	@Value("${mongta.name}")
//	private String name;
	@Value("${mongta.age}")
	private int age;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Logger 구현 클래스 이름: " + logger.getClass().getName());
		logger.debug("MyRunner 시작함");
		logger.debug("Hello Bean: "+ hello);
		logger.debug("--------------------------------------");
//		logger.debug("Name : "+name);
		logger.debug("age : "+age);
		logger.debug("--------------------------------------");
		logger.debug("Name: "+ mongtaProp.getName());
		logger.debug("Age: "+ mongtaProp.getAge());
		logger.debug("fullName: "+ mongtaProp.getFullName());
		logger.info("Program 아규먼트 : " + args.containsOption("bar"));
		logger.info("Vm 아규먼트 : " + args.containsOption("foo"));
	}
}
