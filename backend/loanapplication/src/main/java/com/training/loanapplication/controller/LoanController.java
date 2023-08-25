package com.training.loanapplication.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.serviceInterface.LoanServiceInterface;



@RestController
@CrossOrigin(origins = "http://localhost:5173", maxAge=3600)
public class LoanController {
	
	@Autowired
	LoanServiceInterface loanServiceInterface;
	
	@GetMapping("/getallLoans/{emp_id}")
	public List<Map<String,Object>> getallLoans(@PathVariable String emp_id) throws ResourceNotFoundException
	{
		return loanServiceInterface.getAllLoans(emp_id);
	}
	
	@GetMapping("/getAllTypes")
	public List<String> getAllTypes() throws ResourceNotFoundException
	{
		return loanServiceInterface.getAllTypes();
	}
}
