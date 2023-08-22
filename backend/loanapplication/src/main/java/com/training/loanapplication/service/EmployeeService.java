package com.training.loanapplication.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.loanapplication.dao.CardRepository;
import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.dao.IssueRepository;
import com.training.loanapplication.dao.ItemRepository;
import com.training.loanapplication.dao.LoanRepository;
import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.model.Card;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.Issue;
import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.LoanModel;
import com.training.loanapplication.model.LoginEmployee;
//import com.training.loanapplication.model.LoginResult;
import com.training.loanapplication.model.Message;

import jakarta.validation.Valid;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	LoanRepository loanRepo;
	
	@Autowired
	CardRepository cardRepo;
	
	@Autowired
	IssueRepository issueRepo;
	
	@Autowired
	ItemRepository itemRepo;
	
	// Method to save an employee
	public Employee saveEmployee(@Valid Employee emp)
	{
		if(empRepo.findById(emp.getId()).isPresent()) {
			return null;
		}
		
		return empRepo.save(emp);
	}
	
	// Method to get an employee detail
	public Employee getEmployeeProfile(String emp_id) throws ResourceNotFoundException
	{
		Employee emp = empRepo.findById(emp_id).orElse(null);
		if(emp==null)
		{
			throw new ResourceNotFoundException("No user with this specific Id");
		}
		else
		{
			return emp;
		}
	}
	
	// Method to login an employee
	public Message validateEmployee(LoginEmployee e)
	{
		String result="";
		Employee employee=null;
		Optional<Employee>obj=empRepo.findById(e.getId());
		if(obj.isPresent())
		{
			employee=obj.get();
		}
		if(employee==null)
		{
			result="Invalid Employee Id";
		}
		else
		{
			if(e.getPassword().equals(employee.getPassword()))
			{
				result="Login success";
			}
			else
			{
				result="Login Failed";
			}
		}
		Message loginresult = new Message();
		loginresult.setMessage(result);
		return loginresult;
	}

	// Method for Employee to apply for loan
	@Transactional
	public Message applyLoan(LoanModel loanModel) {
		Card card = new Card();
		Issue issue = new Issue();
		
		Employee emp = empRepo.findById(loanModel.getEmployee_id()).get();
		int loan_id = loanRepo.findIdByType(loanModel.getItem_category());
		Loan loan = loanRepo.findById(loan_id).get();
		Item item = itemRepo.getItemByMakeAndCategoryAndDescription(loanModel.getItem_category(), loanModel.getItem_make(), loanModel.getItem_description());
		
		card.setEmployee(emp);
		card.setLoan(loan);
		card.setDate(LocalDate.now());
		
		Card newCardCreated = cardRepo.save(card);
		if(newCardCreated == null) {
			return new Message("Loan Application Failed");
		}
		
		Short duration = loan.getDuration();
		LocalDate returnDate = newCardCreated.getDate().plusYears((long) duration);
		
		issue.setEmployee(emp);
		issue.setItem(item);
		issue.setIssueDate(newCardCreated.getDate());
		issue.setReturnDate(returnDate);
		
		Issue newIssueCreated = issueRepo.save(issue);
		
		if(newIssueCreated == null) {
			return new Message("Error in issue generation");
		}
		
		return new Message("Sucess");

	}

	// Find all cards issued for employee
	// public List<Employee> findCardByEmployeeId(String emp_id) {
	// 	return empRepo.findCardByEmployeeId(emp_id);
	// }

	// Change password 
	public Message changePassword(Map<String, String> header) {
		Optional<Employee> op = empRepo.findById(header.get("emp_id"));
		
		if(op.isPresent()) {
			Employee emp = op.get();
			
			emp.setPassword(header.get("pwd"));
			empRepo.save(emp);
			return new Message("Password has been updated sucessfully!");
		} else {
			return new Message("No such user found");
		}
	}
}
