package com.mongta.myspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			//URL상의 /mypage/** 가 나오면 인증처리 하겠다
			.antMatchers("/mypage/**").authenticated()
			//나머지 URL은 인증처리 안하겠다
			.antMatchers("/**").permitAll()
			//permitAll()까지 하면 리턴타입이 바뀌므로 and를 써서 httpSecurity 메서드를 호출 할 수 있다.
			.and()
			.formLogin()
			.and()
			.httpBasic()
			//로그아웃------------
			.and()
			.logout() //logout configuration
			.logoutUrl("/app-logout") 
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}