package com.training.loanapplication.service;

//import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.CardRepository;
import com.training.loanapplication.dao.LoanRepository;
import com.training.loanapplication.model.Card;
import com.training.loanapplication.model.Loan;

@Service
public class LoanService {
	
	@Autowired
	LoanRepository loanRepo;
	CardRepository cardRepo;
	
	public Loan saveLoan(Loan loan)
	{
		Loan loan_obj=loanRepo.save(loan);
		return loan_obj;
	}
	
	public List<Loan> getallLoans(Map<String, String> header)
	{
		System.out.println(header.get("emp_id"));
//		List<Card> allCards=cardRepo.findByEmployee_id(header.get("emp_id"));
//		System.out.println(allCards);
		List<Loan> allLoans=loanRepo.findAll();
		return allLoans;
	}
}