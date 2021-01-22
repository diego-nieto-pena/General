package com.admin.service.api;

import java.util.List;
import java.util.Optional;

import com.commons.entity.Role;

public interface IRoleService {
	
	List<Role> getAll();
	
	Optional<Role> findById(Long id);
	
	Role createRole(Role role);
	
	Role updateRole(Role role);
	
	void delete(Long id);
}
