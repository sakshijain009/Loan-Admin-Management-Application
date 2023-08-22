package com.training.loanapplication.serviceInterface;

import java.util.List;

import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.model.Admin;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.Message;

import jakarta.validation.Valid;

public interface AdminServiceInterface {
	public Message checkAdmin(@Valid Admin admin);
	public Message removeEmployee(String employee_id);
	public List<Employee> getAllEmployee() throws ResourceNotFoundException;
	public List<Loan> getAllLoan() throws ResourceNotFoundException;
	public Message addNewEmployee(@Valid Employee e);
	public Message updateEmployee(@Valid Employee e);
	public Message updateItem(@Valid Item item);
	public Message removeItem (int item_id);
	public Message removeLoan (int loan_id);
	public Message updateLoan(@Valid Loan loan);
}
