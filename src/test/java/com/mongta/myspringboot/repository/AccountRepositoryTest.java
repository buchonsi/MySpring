package com.mongta.myspringboot.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mongta.myspringboot.entity.Account;

@SpringBootTest
public class AccountRepositoryTest {
	
	@Autowired
	AccountRepository accountRepo;
	
	@Test
	public void account() throws Exception{
		Account account = new Account();
		String username = "yoon";
		String password = "1234";
		
		account.setUsername(username);
		account.setPassword(password);
		
		//해당 객체를 저장(insert)
		Account savedAcct = accountRepo.save(account);
		System.out.println("Id: "+savedAcct.getId());
		System.out.println("Name: "+savedAcct.getUsername());
		System.out.println("Password: "+savedAcct.getPassword());
		
		//확인(테스트초록불/빨간불)
		assertThat(savedAcct).isNotNull();
		
		Optional<Account> optional = accountRepo.findByUsername(username);
		//값이 있는지
		//System.out.println(optional.isPresent());
		
		if(optional.isPresent()) {
			Account yoon = optional.get();
			System.out.println("yoon[ "+ yoon.getUsername() + " " + yoon.getPassword() + "]");
		}
		
		//
		
		
		
		
		
		
		
	}
}
