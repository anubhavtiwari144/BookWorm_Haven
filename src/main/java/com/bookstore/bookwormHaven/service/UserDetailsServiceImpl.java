package com.bookstore.bookwormHaven.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookstore.bookwormHaven.User;
import com.bookstore.bookwormHaven.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> userOptional = Optional.of(userRepository.findByUsername(username));

		User user = userOptional
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		System.out.println(user.toString());

		return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getPassword()).roles("USER") // or use authorities if needed
				.build();
	}
}
