package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private PasswordEncoder pwdEncoder;

	public List<User> findAll() {
		return (List<User>) repo.findAll();
	}

	public User saveUser(User user) {
		
		EncodePassword(user);
		System.out.println(user);
		return repo.save(user);
	}

	public ResponseEntity<?> findUserById(Integer Id) {
		try {
			User user = repo.findById(Id).get();
			return new ResponseEntity<>(HttpStatus.OK).ok(user.toString());
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST).badRequest().body("User not Found!!");
		}
	}
	
	public ResponseEntity<?> getUserByEmail(String email) {
		try {
			User user = repo.findByEmail(email);
			return new ResponseEntity<>(HttpStatus.OK).ok(user.toString());
		} catch (NullPointerException e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST).badRequest().body("User not Found!!");
		}
	}
	
	public String login (String email , String password) {
		User user = repo.findByEmail(email);
		if(user != null) {
		if(pwdEncoder.matches(password, user.getPassword())) {
			return "Access accepted credentials";
		}
		return "Wrong password or email";
		}
		return "User not found";
	}

	public void EncodePassword(User user) {
		String EncodedPassword = pwdEncoder.encode(user.getPassword());
		System.out.println(EncodedPassword);
		user.setPassword(EncodedPassword);
	}
}
