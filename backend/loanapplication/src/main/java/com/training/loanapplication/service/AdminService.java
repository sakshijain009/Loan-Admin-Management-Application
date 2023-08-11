package com.training.loanapplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.AdminRepository;
import com.training.loanapplication.model.Admin;
//import com.training.loanapplication.model.LoginResult;
import com.training.loanapplication.model.Message;

//import jakarta.validation.Valid;

@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;

	public Message checkAdmin(Admin admin) {
		Optional<Admin> obj = adminRepository.findById(admin.getUsername());
		Admin a = null;
		String result = "";
		if(obj.isPresent()) {
			a = obj.get();
		}
		
		if(a==null) {
			result="Invalid Employee Id";
		}else {
			
			if(admin.getPassword().equals(a.getPassword())) {
				result = "Admin can Log in";
			}else {
				result  = "Password is incorrect";
			}
			
		}
		Message loginresult = new Message();
		loginresult.setMessage(result);
		return loginresult;
		
	}

}
