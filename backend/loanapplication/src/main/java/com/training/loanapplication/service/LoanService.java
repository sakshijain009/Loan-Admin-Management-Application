package com.training.loanapplication.service;

//import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.CardRepository;
import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.dao.LoanRepository;
import com.training.loanapplication.model.Card;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.Loan;

import jakarta.validation.Valid;

@Service
public class LoanService {
	
	@Autowired
	LoanRepository loanRepo;
	
	@Autowired
	CardRepository cardRepo;
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	public Loan saveLoan(Loan loan)
	{
		Loan loan_obj=loanRepo.save(loan);
		return loan_obj;
	}
	
	public List<Map<String,Object>> getallLoans(Map<String, String> header)
	{
		System.out.println(header.get("emp_id"));
		List<Map<String,Object>> allLoans=loanRepo.getallLoans(header.get("emp_id"));
		return allLoans;
	}
}