package com.mongta.myspringboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongta.myspringboot.entity.User;
import com.mongta.myspringboot.entity.Users;
import com.mongta.myspringboot.exception.ResourceNotFoundException;
import com.mongta.myspringboot.repository.UserRepository;

@RestController
public class UserRestController {

	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/users")
	public User create(@RequestBody User user) {
		return userRepo.save(user);
	}
	
	@GetMapping(value = "/users/{id}")
	public User getUser(@PathVariable Long id) {
		Optional<User> optional = userRepo.findById(id);
		User user = optional.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return user;
	}
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return userRepo.findAll();
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User userDetail) {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		user.setName(userDetail.getName());
		user.setEmail(userDetail.getEmail());
		User updatedUser = userRepo.save(user);
		return updatedUser;
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		Optional<User> optional = userRepo.findById(id);
		if(optional.isEmpty()) {
			//id가 맾핑되는 User 객체가 없으면
			return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
		}
		userRepo.deleteById(id);
		return ResponseEntity.ok(id + "번 User 삭제되었음");
	}
	
	@GetMapping(value = "/usersxml", produces = {"application/xml"})
	public Users getUserXml(){
		Users users = new Users();
		users.setUsers(userRepo.findAll());
		return users;
	}
}
