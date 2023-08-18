package com.training.loanapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.loanapplication.model.Admin;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.Message;
import com.training.loanapplication.service.AdminService;
import com.training.loanapplication.service.LoanService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	LoanService loanService;
	
	@PostMapping("/login")
	public Message checkAdmin(@RequestBody @Valid Admin admin)
	{
		return adminService.checkAdmin(admin);	
	}
	
	@PostMapping("/addUser")
	public Employee addNewEmployee(@RequestBody @Valid Employee e)
	{
		return adminService.addNewEmployee(e);	
	}
	
	@GetMapping("/getAllUser")
	public List<Employee> getAllEmployee()
	{
		return adminService.getAllEmployee();	
	}
	
	@PostMapping("/addLoan")
	public Loan addNewLoan(@RequestBody @Valid Loan loan)
	{
		return loanService.saveLoan(loan);	
	}
	
	@DeleteMapping("/removeEmployee/{employee_id}")
	public Message removeEmployee(@PathVariable String employee_id)
	{
		return adminService.removeEmployee(employee_id);	
	}
}
