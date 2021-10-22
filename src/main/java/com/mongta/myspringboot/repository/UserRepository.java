package com.mongta.myspringboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mongta.myspringboot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByName(String name);
	
}
