package com.training.loanapplication.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.serviceInterface.LoanServiceInterface;

import jakarta.validation.Valid;


@RestController
@CrossOrigin("http://localhost:5173") 
public class LoanController {
	
	@Autowired
	LoanServiceInterface loanServiceInterface;
	
	@GetMapping("/getallLoans")
	public List<Map<String,Object>> getallLoans(@RequestHeader Map<String, String> header) throws ResourceNotFoundException
	{
		return loanServiceInterface.getAllLoans(header);
	}
}
