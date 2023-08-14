package com.training.loanapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.LoanSubClass;
import com.training.loanapplication.service.LoanService;

import jakarta.validation.Valid;


@RestController
@CrossOrigin("http://localhost:5173")
public class LoanController {
	
	@Autowired
	LoanService loanService;
	
	@PostMapping("/addLoan")
	public Loan saveLoan(@RequestBody @Valid LoanSubClass loan)
	{
		Loan l = loanService.saveLoan(loan);
		return l;
	}
	
	@GetMapping("/getAllLoans")
	public List<Loan> getallLoans() 
	{
		return loanService.getallLoans();
	}
}
