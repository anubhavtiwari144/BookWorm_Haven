package com.bookstore.bookwormHaven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.bookwormHaven.Book;
import com.bookstore.bookwormHaven.service.BookService;

@Controller
public class BookController {
	
	private final BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		
		this.bookService = bookService;
		
	}
	
	@GetMapping("/books")
	public String getAllBooks(Model model) {
		List<Book> allBooks = bookService.getAllBooks();
		model.addAttribute("books", allBooks);
		return "book/catalog";
	}
	
	@GetMapping("/books/{id}")
	public String getBookDetails(@PathVariable Long id, Model model) {
		Book book = bookService.getBookbyId(id);
		model.addAttribute("book", book);
		return "book/details";
	}
	
	@GetMapping("/search")
	public String searchBooks(@RequestParam String keyword, Model model) {
		List<Book> searchResults = bookService.searchBooks(keyword);
		model.addAttribute("searchResults", searchResults);
		return "book/search";
	}
	
	

}
