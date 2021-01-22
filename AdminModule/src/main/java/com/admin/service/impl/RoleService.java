package com.admin.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.repository.RoleRepository;
import com.admin.service.api.IRoleService;
import com.commons.entity.Role;

@Service
public class RoleService implements IRoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> getAll() {
		return (List<Role>) roleRepository.findAll();
	}

	@Override
	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public Role createRole(Role Role) {
		return roleRepository.save(Role);
	}

	@Override
	public Role updateRole(Role Role) {
		return roleRepository.save(Role);
	}

	@Override
	public void delete(Long id) {
		roleRepository.deleteById(id);
		
	}

}
