package com.bookstore.bookwormHaven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookstore.bookwormHaven.service.BookService;

@Controller
public class HomeController {
	
	private final BookService bookService;
	
	@Autowired
	public HomeController(BookService bookService) {
		
		this.bookService = bookService;
		
	}
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("featuredBooks",bookService.getFeaturedBooks());
		model.addAttribute("newArrivals", bookService.getNewArrivals());
		
		return "home";
	}
	
	

}
