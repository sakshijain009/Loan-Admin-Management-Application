package com.training.loanapplication.serviceInterface;

import java.util.List;
import java.util.Map;

import com.training.loanapplication.exception.DuplicateEntryException;
import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.Message;

import jakarta.validation.Valid;

public interface LoanServiceInterface {
	public Message saveLoan(@Valid Loan loan) throws DuplicateEntryException;
	public Loan getLoanById(int loan_id) throws ResourceNotFoundException ;
	public List<Map<String,Object>> getAllLoans(String emp_id) throws ResourceNotFoundException;
	public List<String> getAllTypes() throws ResourceNotFoundException;
}
