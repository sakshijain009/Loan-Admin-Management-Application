package com.training.loanapplication.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.LoanModel;
import com.training.loanapplication.model.LoginEmployee;
//import com.training.loanapplication.model.LoginResult;
import com.training.loanapplication.model.Message;
import com.training.loanapplication.serviceInterface.EmployeeServiceInterface;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/users")
public class EmployeeController {
	
	@Autowired
	EmployeeServiceInterface empServiceInterface;
	
	@PostMapping("/addUser")
	public Employee saveEmployee(@RequestBody @Valid Employee emp)
	{
		return empServiceInterface.saveEmployee(emp);
	}
	
	@GetMapping("/profile/{emp_id}")
	public Employee getEmployeeProfile(@PathVariable String emp_id) throws ResourceNotFoundException
	{
		return empServiceInterface.getEmployeeProfile(emp_id);
	}
	
	@PostMapping("/checkLogin")
	public Message validateEmployee(@RequestBody LoginEmployee e)
	{
		return empServiceInterface.validateEmployee(e);
	}
	
	@PostMapping("/applyLoan")
	public Message applyLoan(@RequestBody LoanModel loanModel)
	{
		return empServiceInterface.applyLoan(loanModel);
	}
	
//	@GetMapping("/getCards/{emp_id}")
//	public List<Employee> findCardByEmployeeId(@PathVariable String emp_id)
//	{
//		return empService.findCardByEmployeeId(emp_id);
//	}
	
	@PutMapping("/changePassword")
	public Message changePassword(@RequestHeader Map<String, String> header)
	{
		return empServiceInterface.changePassword(header);
	}
}
