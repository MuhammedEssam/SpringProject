package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userservice;
	
	@GetMapping("/users")
	 public List<User> getUsers(){
		return (List<User>) userservice.findAll();
	}
	
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return userservice.saveUser(user);
	}
	
	@GetMapping("/users/{UserId}")
	public ResponseEntity<?> getUserById(@PathVariable Integer UserId) {
		return userservice.findUserById(UserId);
	}
	
	@PostMapping("/userByEmail")
	public ResponseEntity<?> getUserByEmail(@Param(value = "email") String email){
		return userservice.getUserByEmail(email);
	}
	
	@PostMapping("/login")
	public String login(@Param(value = "password") String password , @Param(value = "email") String email) {
		return userservice.login(email, password);
	}
	
}
