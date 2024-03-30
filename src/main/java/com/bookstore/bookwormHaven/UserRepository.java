package com.bookstore.bookwormHaven;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
