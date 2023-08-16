package com.training.loanapplication.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.CardRepository;
import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.dao.IssueRepository;
import com.training.loanapplication.dao.ItemRepository;
import com.training.loanapplication.dao.LoanRepository;
import com.training.loanapplication.model.Card;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.Issue;
import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.LoanModel;
import com.training.loanapplication.model.LoginEmployee;
//import com.training.loanapplication.model.LoginResult;
import com.training.loanapplication.model.Message;

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
	
	public Employee saveEmployee(Employee emp)
	{
		Employee emp_obj=empRepo.save(emp);
		return emp_obj;
	}
	
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

	public Message applyLoan(LoanModel loanModel) {
		Card card = new Card();
		Issue issue = new Issue();
		
		Employee emp = empRepo.findById(loanModel.getEmployee_id()).get();
		int loan_id = loanRepo.findIdByType(loanModel.getItem_category());
		Loan loan = loanRepo.findById((long) loan_id).get();
		Item item = itemRepo.getItemByMakeAndCategoryAndDescription(loanModel.getItem_category(), loanModel.getItem_make(), loanModel.getItem_description());
		
		card.setEmployee(emp);
		card.setLoan(loan);
		card.setDate(LocalDate.now());
		
		Card newCardCreated = cardRepo.save(card);
		if(newCardCreated == null) {
			return new Message("Loan Application Failed");
		}
		
		issue.setEmployee(emp);
		issue.setItem(item);
		issue.setIssueDate(newCardCreated.getDate().toString());
		issue.setReturnDate(null);
		
		Issue newIssueCreated = issueRepo.save(issue);
		
		if(newIssueCreated == null) {
			return new Message("Error in issue generation");
		}
		
		return new Message("Sucess");

	}
}
