package com.bookstore.bookwormHaven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.bookstore.bookwormHaven.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;

    @Bean
    static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
        http
        .formLogin(form -> form.loginPage("/login").permitAll())
        .authorizeHttpRequests(authorize -> authorize.requestMatchers("/", "/home","/register", "/login2").permitAll().anyRequest().authenticated());
		
		return http.build();
	}

//    @Bean
//    UserDetailsService userDetailsService() {
//		UserDetails user = User.builder()
//				.username("anubhav")
//				.password(passwordEncoder().encode("anubhav"))
//				.roles("USER")
//				.build();
//		return new InMemoryUserDetailsManager(user);
//	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
