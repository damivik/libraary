package com.github.damivik.libraary.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.damivik.libraary.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	public boolean existsByEmail(String email);
	
	public User findByEmail(String email);
}
