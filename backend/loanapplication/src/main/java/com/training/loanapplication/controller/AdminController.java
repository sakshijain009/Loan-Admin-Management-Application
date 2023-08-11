package com.training.loanapplication.controller;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.loanapplication.model.Admin;
//import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.LoginResult;
import com.training.loanapplication.service.AdminService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@PostMapping("/admin")
	public LoginResult checkAdmin(@RequestBody @Valid Admin admin)
	{
		return adminService.checkAdmin(admin);
		
	}
}
