package com.training.loanapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.CardRepository;
import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.dao.LoanRepository;
import com.training.loanapplication.model.Card;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.LoanSubClass;

import jakarta.validation.Valid;

@Service
public class LoanService {
	
	@Autowired
	LoanRepository loanRepo;
	
	@Autowired
	CardRepository cardRepo;
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	public Loan saveLoan(@Valid LoanSubClass loan)
	{
		Loan loan_data = new Loan(loan.getType(), loan.getDuration());	
		Loan loan_obj = loanRepo.save(loan_data);
		
		Optional<Employee> employee = employeeRepo.findById(loan.getEmployee_id());
		Employee emp = null;
		if(employee.isPresent()) {
			emp = employee.get();
		} 
		
		if(emp == null) return null;
		
		Card card_obj = new Card();
		card_obj.setDate(loan.getDate());
		card_obj.setLoan(loan_obj);
		card_obj.setEmployee(emp);
		
		Card newCard = cardRepo.save(card_obj);
		
//		loan_obj.setCard(newCard);
		
		return loan_obj;
	}
	
	public List<Loan> getallLoans()
	{
		List<Loan> allLoans=loanRepo.findAll();
		return allLoans;
	}
}