package com.admin.service.api;

import java.util.List;
import java.util.Optional;

import com.commons.entity.User;

public interface IUserService {
	
	List<User> getAll();
	
	Optional<User> findById(Long id);
	
	User createUser(User user);
	
	User updateUser(User user);
	
	void delete(Long id);
}
