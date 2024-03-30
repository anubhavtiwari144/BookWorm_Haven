package com.bookstore.bookwormHaven.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.bookstore.bookwormHaven.User;
import com.bookstore.bookwormHaven.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String showRegisterationForm(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user) {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
    	String hashPwd = bc.encode(user.getPassword());
    	user.setPassword(hashPwd);
		userService.saveUser(user);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String showLoginForm() {
		System.out.println();
		return "login";
	}
	
	@PostMapping("login")
	public Map<String, String> loginUser() {
		System.out.println("akshdgf");
//		System.out.println(map.toString());
		Map<String, String> map = new HashMap<>();
		return map;
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/home";
	}
}
