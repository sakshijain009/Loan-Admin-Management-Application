package com.training.loanapplication.service;

//import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.CardRepository;
import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.dao.LoanRepository;
import com.training.loanapplication.model.Loan;

@Service
public class LoanService {
	
	@Autowired
	LoanRepository loanRepo;
	
	@Autowired
	CardRepository cardRepo;
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	// Method to save loan in loan table
	public Loan saveLoan(Loan loan)
	{
//		if(loanRepo.findByType(loan.getType()).) {
//			return null;
//		}
		
		return loanRepo.save(loan);
	}
	
	// Method to get all loans for a particular Employee
	public List<Map<String,Object>> getAllLoans(Map<String, String> header)
	{
		System.out.println(header.get("emp_id"));
		List<Map<String,Object>> allLoans=loanRepo.getallLoans(header.get("emp_id"));
		return allLoans;
	}
}