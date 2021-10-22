package com.mongta.myspringboot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mongta.myspringboot.entity.Account;
import com.mongta.myspringboot.property.MongtaProperties;
import com.mongta.myspringboot.repository.AccountRepository;

@Service
public class AccountService implements UserDetailsService{
	private final AccountRepository accountRepository;
	private final MongtaProperties property;
	private final PasswordEncoder encoder;
	
	public AccountService(AccountRepository accountRepository, MongtaProperties property, PasswordEncoder encoder) {
		this.accountRepository = accountRepository;
		this.property = property;
		this.encoder= encoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> optional = accountRepository.findByUsername(username);
		Account account = optional.orElseThrow(() -> new UsernameNotFoundException(username));
		
		return new User(account.getUsername(), account.getPassword(), authorities());
	}
	
	private Collection<? extends GrantedAuthority> authorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@PostConstruct
	public void insert() {
		Optional<Account> optional = accountRepository.findByUsername(property.getUsername());
		if(optional.isEmpty()) {
			Account account = createAccount(property.getUsername(), property.getPassword());
			System.out.println("Account : " + account);
		}
	}
	
	// Account 레코드 추가
	public Account createAccount(String username, String password) {
		Account account = new Account();
		account.setUsername(username);
		//account.setPassword(password);
		//암호화
		account.setPassword(encoder.encode(password));
		return accountRepository.save(account);
	}

}
