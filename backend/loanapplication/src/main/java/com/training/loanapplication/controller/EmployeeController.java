package com.training.loanapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.loanapplication.model.Employee;
import com.training.loanapplication.service.EmployeeService;

@RestController
@CrossOrigin("http://localhost:3000")
public class EmployeeController {
	@Autowired
	EmployeeService empService;
	@PostMapping("/addUser")
	public Employee saveEmployee(@RequestBody Employee emp)
	{
		Employee e=empService.saveEmployee(emp);
		return e;
	}
}
