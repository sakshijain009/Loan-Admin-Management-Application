package com.training.loanapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="admin_master")

public class Admin {
	
	@Id
	@Column(name="admin_username")
	@NotNull(message="Username cannot be null")
	@NotBlank(message="Username cannot be blank")
	private String username;
	
	@Column(name="admin_password")
	@NotNull(message="Password cannot be null")
	@NotBlank(message="Password cannot be blank")
	@Size(min=6, message="Minimum password length should be 6")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
