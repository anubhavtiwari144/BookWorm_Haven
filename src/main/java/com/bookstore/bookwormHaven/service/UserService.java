package com.bookstore.bookwormHaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.bookwormHaven.User;
import com.bookstore.bookwormHaven.UserRepository;

@Service
public class UserService {

	@Autowired
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	public User getByUsername(String usernName) {
		return userRepository.findByUsername(usernName);
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
}
