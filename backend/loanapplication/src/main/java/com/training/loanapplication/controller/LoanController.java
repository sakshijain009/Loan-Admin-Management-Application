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

import com.training.loanapplication.model.Loan;
import com.training.loanapplication.service.LoanService;

import jakarta.validation.Valid;


@RestController
@CrossOrigin("http://localhost:5173")
public class LoanController {
	
	@Autowired
	LoanService loanService;
	
	@PostMapping("/addLoan")
	public Loan saveLoan(@RequestBody @Valid Loan loan)
	{
		Loan l = loanService.saveLoan(loan);
		return l;
	}
	
	@GetMapping("/getallLoans")
	public List<Map<String,Object>> getallLoans(@RequestHeader Map<String, String> header) 
	{
		return loanService.getallLoans(header);
	}
}
