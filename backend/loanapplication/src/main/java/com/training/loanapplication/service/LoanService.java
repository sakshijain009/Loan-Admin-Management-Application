package com.training.loanapplication.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
//import java.util.Date;
//import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.CardRepository;
import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.dao.LoanRepository;
import com.training.loanapplication.exception.DuplicateEntryException;
import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.Message;
import com.training.loanapplication.serviceInterface.LoanServiceInterface;

import jakarta.validation.Valid;

@Service
public class LoanService implements LoanServiceInterface {
	
	@Autowired
	LoanRepository loanRepo;
	
	@Autowired
	CardRepository cardRepo;
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	// Method to save loan in loan table
	public Message saveLoan (@Valid Loan loan) throws DuplicateEntryException
	{
		Loan checkLoanExists = loanRepo.findByType(loan.getType());
		
		if(checkLoanExists == null) {
			loanRepo.save(loan);
			return new Message("Loan has been added successfully");
		}else {
			throw new DuplicateEntryException("Loan for the type already exists!");
		}
	}
	
	public Loan getLoanById(int loan_id) throws ResourceNotFoundException
	{
		Loan l =  loanRepo.findById(loan_id).orElse(null);
		
		if(l==null) {
			throw new ResourceNotFoundException("No Loan for this particular loan id");
		}
		else
			return l;
	}
	
	// Method to get all loans for a particular Employee
	public List<Map<String,Object>> getAllLoans(String emp_id) throws ResourceNotFoundException
	{
		List<Map<String,Object>> allLoans = loanRepo.getallLoans(emp_id);
		
		List<Map<String,Object>> updated_loans = new ArrayList<>();
		
		for(int i = 0;i < allLoans.size(); i++) {
			LocalDate issue_date =  ((Date) allLoans.get(i).get("card_issue_date")).toLocalDate();
			LocalDate return_date = issue_date.plusYears(Long.valueOf(allLoans.get(i).get("duration").toString()));
			if(return_date.isAfter(LocalDate.now())) updated_loans.add(allLoans.get(i));
		}

		if(updated_loans.size()==0){
			throw new ResourceNotFoundException("No active Loans to display for this employee");
		}
		else {
			return updated_loans;
		}	
	}
	
	// Get all loan types
		public List<String> getAllTypes() throws ResourceNotFoundException
		{
			List<String> all_types = loanRepo.getAllTypes();
			if(all_types.size()==0) {
				throw new ResourceNotFoundException("No loan type available");
			} else
				return all_types; 
		}
}