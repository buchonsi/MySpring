package com.mongta.myspringboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mongta.myspringboot.entity.User;
import com.mongta.myspringboot.repository.UserRepository;

@Controller
public class UserController {
	
	private final UserRepository userRepository;
	
	public UserController(UserRepository repository) {
		this.userRepository = repository;
	}
	
	//시큐리티
	@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(user);
		model.addAttribute("users", userRepository.findAll());
		return "index";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		model.addAttribute("user", user);
		return "update-user";
	}

	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "update-user";
		}
		userRepository.save(user);
		model.addAttribute("users", userRepository.findAll());
		return "index";
	}
	
	@PostMapping("/adduser")
	public String addUser(@Valid User user, BindingResult result) {
		//입력항목 검증 오류가 있으면
		if(result.hasErrors()) {
			return "add-user";
		}
		userRepository.save(user);
		//User 리스트 요청하는 url로 Redirect해라
		return "redirect:/index";
	}
	
	@GetMapping("/signup")
	public String showSignUpForm(User myuser) {
		return "add-user";
	}
	
	@GetMapping("/index")
	public ModelAndView index() {
		List<User> userList = userRepository.findAll();
		return new ModelAndView("index", "users", userList);
	}
	@GetMapping("/thymeleaf")
	public String leaf(Model model) {
		model.addAttribute("name", "몽타");
		return "leaf";
	}
	
	
}
