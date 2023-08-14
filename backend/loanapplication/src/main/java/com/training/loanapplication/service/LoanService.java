package com.training.loanapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.LoanRepository;
import com.training.loanapplication.model.Loan;

@Service
public class LoanService {
	
	@Autowired
	LoanRepository loanRepo;
	
	public Loan saveLoan(Loan loan)
	{
		Loan loan_obj=loanRepo.save(loan);
		return loan_obj;
	}
	
	public List<Loan> getallLoans()
	{
		List<Loan> allLoans=loanRepo.findAll();
		return allLoans;
	}
}