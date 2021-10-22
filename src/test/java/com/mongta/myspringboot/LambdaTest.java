package com.mongta.myspringboot;

import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class LambdaTest {
	
	@Test
	public void iterable() throws Exception{
		//list.of는 add를 못한다.
		List<String> list = List.of("aa","bb","cc");
		
		list.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
		
		System.out.println("----------------------------------");
		
		//Lambda Experssion
		list.forEach(t->{
			System.out.println(t);
		});
		System.out.println("----------------------------------");
		//Method Reference
		list.forEach(System.out::println);
		
	}
	
	
	@Test
	@Disabled
	public void lamda() throws Exception {
		//1. Anonymous InnerClass 형태로 Runnable의 run()을 재정의 하기
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		});
		
		t1.setName("yoon");
		t1.start();
		
		Thread t2 = new Thread(()->{
			System.out.println(Thread.currentThread().getName());
		});
		t2.start();
		
	}
}
