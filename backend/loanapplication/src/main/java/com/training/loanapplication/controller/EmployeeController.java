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

import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.LoanModel;
import com.training.loanapplication.model.LoginEmployee;
//import com.training.loanapplication.model.LoginResult;
import com.training.loanapplication.model.Message;
import com.training.loanapplication.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/users")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	@PostMapping("/addUser")
	public Employee saveEmployee(@RequestBody @Valid Employee emp)
	{
		return empService.saveEmployee(emp);
	}
	
	@GetMapping("/profile/{emp_id}")
	public Employee getEmployeeProfile(@PathVariable String emp_id)
	{
		return empService.getEmployeeProfile(emp_id);
	}
	
	@PostMapping("/checkLogin")
	public Message validateEmployee(@RequestBody LoginEmployee e)
	{
		return empService.validateEmployee(e);
	}
	
	@PostMapping("/applyLoan")
	public Message applyLoan(@RequestBody LoanModel loanModel)
	{
		return empService.applyLoan(loanModel);
	}
	
//	@GetMapping("/getCards/{emp_id}")
//	public List<Employee> findCardByEmployeeId(@PathVariable String emp_id)
//	{
//		return empService.findCardByEmployeeId(emp_id);
//	}
	
	@PutMapping("/changePassword")
	public Message changePassword(@RequestHeader Map<String, String> header)
	{
		return empService.changePassword(header);
	}
}
