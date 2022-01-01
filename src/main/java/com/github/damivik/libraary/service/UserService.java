package com.github.damivik.libraary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.damivik.libraary.dto.SignupDto;
import com.github.damivik.libraary.entity.User;
import com.github.damivik.libraary.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public User register(SignupDto dto) {
		User user = new User(dto.getEmail(), passwordEncoder.encode(dto.getPassword()));
		
		return userRepository.save(user);
	}
	
}