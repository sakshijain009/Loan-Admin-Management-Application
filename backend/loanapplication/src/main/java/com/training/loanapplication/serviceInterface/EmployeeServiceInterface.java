package com.training.loanapplication.serviceInterface;

import java.util.Map;

import com.training.loanapplication.exception.AuthenticationFailedException;
import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.LoanModel;
import com.training.loanapplication.model.LoginEmployee;
import com.training.loanapplication.model.Message;

import jakarta.validation.Valid;

public interface EmployeeServiceInterface {
	public Employee saveEmployee(@Valid Employee emp) throws AuthenticationFailedException;
	public Employee getEmployeeProfile(String emp_id) throws ResourceNotFoundException;
	public Message validateEmployee(LoginEmployee e) throws AuthenticationFailedException;
	public Message applyLoan(LoanModel loanModel);
	public Message changePassword(Map<String, String> header);
}
