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
import org.springframework.web.bind.annotation.RestController;

import com.admin.exception.EntityNotFoundException;
import com.admin.service.impl.RoleService;
import com.commons.entity.Role;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@GetMapping("/role")
	public List<Role> findAll() {
		return roleService.getAll();
	}
	
	@PostMapping("/role")
	public Role create(@RequestBody Role role) {
		log.error("Role: " + role);
		return roleService.createRole(role);
	}
	
	@PutMapping("/role/{id}")
	public Role update(@RequestBody Role role, @PathVariable Long id) throws EntityNotFoundException {
		Optional<Role> optRole = roleService.findById(id);
		
		if(optRole.isPresent()) {
			role.setId(id);
			roleService.updateRole(role);
		} else {
			throw new EntityNotFoundException("Role not found!");
		}
		
		return role;
	}
	
	@DeleteMapping("/role/{id}")
	public void delete(@PathVariable Long id) {
		roleService.delete(id);
	}
	
}
