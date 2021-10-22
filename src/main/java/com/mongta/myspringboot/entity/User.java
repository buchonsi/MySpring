package com.mongta.myspringboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@Entity
public class User {
	
	//@JacksonXmlProperty(isAttribute = true) >> 루트 테그의 속성으로 들어감
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JacksonXmlProperty(isAttribute = true)
	private Long id;
	
	@NotBlank(message = "Name은 필수입력 항목입니다!")
	@JacksonXmlProperty
	private String name;
	
	@NotBlank(message = "Email은 필수입력 항목입니다!")
	@Column(unique = true)
	@JacksonXmlProperty
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
