package com.bookstore.bookwormHaven;

import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ElementCollection
	@CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
	@MapKeyColumn(name = "book_id")
	@Column(name = "quantity")
	private Map<Long, Integer> orderItems;
	
	@Column(name = "total_price")
	private double totalPrice;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Map<Long, Integer> getOrderItems(){
		return getOrderItems();
	}
	
	public void setOrderItems(Map<Long, Integer> orderItems) {
		this.orderItems = orderItems;
	}

}
