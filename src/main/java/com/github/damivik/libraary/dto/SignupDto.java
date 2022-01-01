package com.github.damivik.libraary.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.github.damivik.libraary.validation.UniqueEmail;

public class SignupDto {
	
	@NotBlank
	@Size(max = 255)
	@Email
	@UniqueEmail
	private String email;

	@NotBlank
	@Size(min = 8, max = 30)
	private String password;
	
	public SignupDto(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}