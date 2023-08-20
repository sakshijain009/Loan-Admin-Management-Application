package com.training.loanapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.AdminRepository;
import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.dao.ItemRepository;
import com.training.loanapplication.dao.LoanRepository;
import com.training.loanapplication.model.Admin;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.Message;


@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	EmployeeRepository empRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	LoanRepository loanRepository;

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
	public Message addNewEmployee(Employee e) {
		if(empRepository.findById(e.getId()).isPresent()) {
			return new Message("Employee already existss");
		}
		
		empRepository.save(e);
		return new Message("Employee successfully added");
	}
	
	// Function for admin to add update employee
	public Message updateEmployee(Employee e) {
		Optional<Employee> op = empRepository.findById(e.getId());
			
		if(op.isPresent()) {
			empRepository.save(e);
			return new Message("Employee details successfully updated");
		} else {
			return new Message("No such employee is present");
		}
	}
	
	// Function for admin to remove an item
		public Message removeItem (int item_id) {
			
			if(itemRepository.findById(item_id).isPresent()) {
				itemRepository.deleteById(item_id);
				
				return new Message("Item has been successfully deleted");
			}else {
				return new Message("Error: No such item found!");
			}
			
		}
		
	// Function for admin to remove a loan
		public Message removeLoan (int loan_id) {
			
			if(loanRepository.findById(loan_id).isPresent()) {
				loanRepository.deleteById(loan_id);
				
				return new Message("Loan has been successfully deleted");
			}else {
				return new Message("Error: No such loan found!");
			}
			
		}
}
