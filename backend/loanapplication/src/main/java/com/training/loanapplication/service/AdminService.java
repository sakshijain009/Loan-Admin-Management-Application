package com.training.loanapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.AdminRepository;
import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.model.Admin;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.Message;


@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	EmployeeRepository empRepository;

	// Check if admin details are correct
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
		
		return new Message(result);
	}
	
	// Function for admin to remove employee
	public Message removeEmployee(String employee_id) {
		
		if(empRepository.findById(employee_id).isPresent()) {
			empRepository.deleteById(employee_id);
			
			return new Message("Employee has been successfully deleted");
		}else {
			return new Message("Error: No such user found!");
		}
		
	}
	
	// Function for admin to get all employee
	public List<Employee> getAllEmployee() {
		return empRepository.findAll();
	}
	
	// Function for admin to add new employee
	public Employee addNewEmployee(Employee e) {
		if(empRepository.findById(e.getId()).isPresent()) {
			return null;
		}
		
		return empRepository.save(e);
	}
}
