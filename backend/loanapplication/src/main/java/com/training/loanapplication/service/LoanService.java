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
import com.training.loanapplication.model.LoanSubClass;

import jakarta.validation.Valid;

@Service
public class LoanService {
	
	@Autowired
	LoanRepository loanRepo;
	CardRepository cardRepo;
	
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
	
	public List<Loan> getallLoans(Map<String, String> header)
	{
		System.out.println(header.get("emp_id"));
//		List<Card> allCards=cardRepo.findByEmployee_id(header.get("emp_id"));
//		System.out.println(allCards);
		List<Loan> allLoans=loanRepo.findAll();
		return allLoans;
	}
}