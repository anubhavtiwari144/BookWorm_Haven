package com.bookstore.bookwormHaven.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.bookwormHaven.Book;
import com.bookstore.bookwormHaven.BookRepository;

@Service
public class BookService {
	
	private final BookRepository bookRepository;
	
	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	public Book getBookbyId(Long Id) {
		return bookRepository.findById(Id).orElse(null);
	}
	
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}
	
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
	
	public List<Book> getFeaturedBooks(){
		return bookRepository.findTop5ByOrderByTitleAsc();
	}
	
	public List<Book> getNewArrivals(){
		return bookRepository.findTop5ByOrderByTitleDesc();
	}
	
	public List<Book> searchBooks(String keyword){
		return bookRepository.findByTitleContainingIgnoreCase(keyword);
	}

}
