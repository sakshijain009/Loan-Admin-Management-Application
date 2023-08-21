package com.training.loanapplication.service;

//import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.CardRepository;
import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.dao.LoanRepository;
import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.Message;

import jakarta.validation.Valid;

@Service
public class LoanService {
	
	@Autowired
	LoanRepository loanRepo;
	
	@Autowired
	CardRepository cardRepo;
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	// Method to save loan in loan table
	public Message saveLoan(@Valid Loan loan)
	{
		loanRepo.save(loan);
		return new Message("Loan has been added successfully");
	}
	
	public Loan getLoanById(int loan_id)
	{
		return loanRepo.findById(loan_id).orElse(null);
	}
	// Method to get all loans for a particular Employee
	public List<Map<String,Object>> getAllLoans(Map<String, String> header) throws ResourceNotFoundException
	{
//		System.out.println(header.get("emp_id"));
		List<Map<String,Object>> allLoans=loanRepo.getallLoans(header.get("emp_id"));
		if(allLoans.size()==0)
		{
			throw new ResourceNotFoundException("No Loans to display for this employee");
		}
		else
		return allLoans;
	}
}