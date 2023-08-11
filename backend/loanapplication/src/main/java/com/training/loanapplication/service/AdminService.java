package com.training.loanapplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.AdminRepository;
import com.training.loanapplication.model.Admin;

import jakarta.validation.Valid;

@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;

	public Admin checkAdmin(@Valid Admin admin) {
		Optional<Admin> obj = adminRepository.findById(admin.getUsername());
		Admin a = null;
		
		if(obj.isPresent()) {
			a = obj.get();
		}
		
		if(a==null) {
			return null;
		}else {
			if(admin.getPassword().equals(a.getPassword())) {
				String result = "Admin can Log in";
			}else {
				String result  = "Password is incorrect";
			}
		}
		
		return null;
	}

}
