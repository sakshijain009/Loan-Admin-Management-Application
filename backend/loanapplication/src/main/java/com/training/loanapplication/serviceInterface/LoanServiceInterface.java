package com.training.loanapplication.serviceInterface;

import java.util.List;
import java.util.Map;

import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.Message;

import jakarta.validation.Valid;

public interface LoanServiceInterface {
	public Message saveLoan(@Valid Loan loan);
	public Loan getLoanById(int loan_id) throws ResourceNotFoundException ;
	public List<Map<String,Object>> getAllLoans(Map<String, String> header) throws ResourceNotFoundException;
	public List<String> getAllTypes() throws ResourceNotFoundException;
}
