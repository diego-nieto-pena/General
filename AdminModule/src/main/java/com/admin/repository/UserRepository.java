package com.admin.repository;

import org.springframework.data.repository.CrudRepository;

import com.commons.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
