package com.admin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.exception.UserNotFoundException;
import com.admin.service.impl.UserService;
import com.commons.entity.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public List<User> findAll() {
		return userService.getAll();
	}
	
	@PostMapping("/user")
	public User create(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@PutMapping("/user/{id}")
	public User update(@RequestBody User user, @PathVariable Long id) throws UserNotFoundException {
		Optional<User> optUser = userService.findById(id);
		
		if(optUser.isPresent()) {
			user.setId(id);
			userService.updateUser(user);
		} else {
			throw new UserNotFoundException("User not found!");
		}
		
		return user;
	}
	
	@DeleteMapping("/user/{id}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}
	
}
