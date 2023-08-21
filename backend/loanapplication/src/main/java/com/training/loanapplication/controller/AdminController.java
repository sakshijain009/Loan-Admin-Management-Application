package com.training.loanapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.model.Admin;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.Message;
import com.training.loanapplication.service.AdminService;
import com.training.loanapplication.service.ItemService;
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
	
	@Autowired
	ItemService itemService;
	
	@PostMapping("/login")
	public Message checkAdmin(@RequestBody @Valid Admin admin)
	{
		return adminService.checkAdmin(admin);	
	}
	
	@PostMapping("/addUser")
	public Message addNewEmployee(@RequestBody @Valid Employee e)
	{
		return adminService.addNewEmployee(e);	
	}
	
	@PutMapping("/updateUser")
	public Message updateEmployee(@RequestBody @Valid Employee e)
	{
		return adminService.updateEmployee(e);	
	}
	
	@GetMapping("/getAllUser")
	public List<Employee> getAllEmployee() throws ResourceNotFoundException
	{
		return adminService.getAllEmployee();	
	}
	
	@GetMapping("/getAllLoan")
	public List<Loan> getAllLoan() throws ResourceNotFoundException
	{
		return adminService.getAllLoan();
	}
	
	@PostMapping("/addLoan")
	public Message addNewLoan(@RequestBody @Valid Loan loan)
	{
		return loanService.saveLoan(loan);	
	}
	
	@DeleteMapping("/removeEmployee/{employee_id}")
	public Message removeEmployee(@PathVariable String employee_id)
	{
		return adminService.removeEmployee(employee_id);	
	}
	
	@DeleteMapping("/removeLoan/{loan_id}")
	public Message removeLoan(@PathVariable int loan_id)
	{
		return adminService.removeLoan(loan_id);	
	}
	
	@PutMapping("/updateLoan")
	public Message updateLoan(@RequestBody @Valid Loan loan)
	{
		return adminService.updateLoan(loan);	
	}
	
	@DeleteMapping("/removeItem/{item_id}")
	public Message removeItem(@PathVariable int item_id)
	{
		return adminService.removeItem(item_id);	
	}
	
	@PutMapping("/updateItem")
	public Message updateItem(@RequestBody @Valid Item item)
	{
		return adminService.updateItem(item);	
	}
	
	@PostMapping("/addItem")
	public Item addItem(@RequestBody @Valid Item item)
	{
		return itemService.saveItem(item);	
	}
}
