package com.bookstore.bookwormHaven.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.bookwormHaven.Book;
import com.bookstore.bookwormHaven.service.BookService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {
	
	private final BookService bookService;
	
	@Autowired
	public ShoppingCartController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping
	public String viewCart(Model model, HttpSession session) {
		Map<Long, Integer> cart = getOrCreateCart(session);
		model.addAttribute("cart",cart);		
		return "cart/view";
	}
	
	@PostMapping("/add/{bookId}")
	public String addToCart(@PathVariable Long bookId, @RequestParam int quantity, HttpSession session ) {
		Map<Long, Integer> cart = getOrCreateCart(session);
		cart.put(bookId, cart.getOrDefault(bookId, 0) + quantity);
		return "redirect:/cart";
	}
	
	@PostMapping("/update/{bookId}")
	public String updateCart(@PathVariable Long bookId, @RequestParam int quantity, HttpSession session) {
		Map<Long, Integer> cart = getOrCreateCart(session);
		if (quantity > 0) {
			cart.put(bookId, quantity);
		} else {
			cart.remove(bookId);
		}
		
		return "redirect:/cart";
	}
	
	@PostMapping
	public String removeFromCart(@PathVariable Long bookId, HttpSession session) {
		Map<Long, Integer> cart = getOrCreateCart(session);
		cart.remove(bookId);
		return "redirect:/cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model, HttpSession session) {
		Map<Long, Integer> cart = getOrCreateCart(session);
		// You can implement checkout logic here, e.g., calculating total price, processing orders, etc.
		double totalPrice = calculateTotalPrice(cart);
		
        // For simplicity, we'll just display the cart contents in the checkout view
		model.addAttribute("cart", cart);
		model.addAttribute("totalPrice", totalPrice);
		return "cart/ckeckout";
	}
	
	private double calculateTotalPrice(Map<Long, Integer> cart) {
		double totalPrice=0.0;
		
		for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
			Long bookId = entry.getKey();
			int quantity = entry.getValue();
			
			Book book = bookService.getBookbyId(bookId);
			
			double subTotal = book.getPrice() * quantity;
			
			totalPrice += subTotal;
		}
		
		return totalPrice;
	}
	
	private Map<Long, Integer> getOrCreateCart(HttpSession session ){
		Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
		
		if (cart==null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}
		
		return cart;
	}

}
