package com.training.loanapplication.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.AdminRepository;
import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.dao.IssueRepository;
import com.training.loanapplication.dao.ItemRepository;
import com.training.loanapplication.dao.LoanRepository;
import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.model.Admin;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.Issue;
import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.Message;

import jakarta.validation.Valid;


@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	EmployeeRepository empRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	IssueRepository issueRepository;
	
	@Autowired
	LoanRepository loanRepository;

	// Check if admin details are correct
	public Message checkAdmin(@Valid Admin admin) {
		Optional<Admin> obj = adminRepository.findById(admin.getUsername());
		Admin a = null;
		String result = "";
		if(obj.isPresent()) {
			a = obj.get();
		}
		
		if(a==null) {
			result="Invalid Employee Id";
		}else {
			
			if(admin.getPassword().equals(a.getPassword())) {
				result = "Admin can Log in";
			}else {
				result  = "Password is incorrect";
			}
			
		}
		
		return new Message(result);
	}
	
	// Function for admin to remove employee
	public Message removeEmployee(String employee_id) {
		
		if(empRepository.findById(employee_id).isPresent()) {
			empRepository.deleteById(employee_id);
			
			return new Message("Employee has been successfully deleted");
		}else {
			return new Message("Error: No such user found!");
		}
		
	}
	
	// Function for admin to get all employee
	public List<Employee> getAllEmployee() throws ResourceNotFoundException{
		List<Employee> all_emps= empRepository.findAll();
		if(all_emps.size()==0)
		{
			throw new ResourceNotFoundException("No employees to show");
		}
		else
		return all_emps;
	}
	
	// Function for admin to get all loan
		public List<Loan> getAllLoan() throws ResourceNotFoundException{
			List<Loan> all_loans= loanRepository.findAll();
			if(all_loans.size()==0)
			{
				throw new ResourceNotFoundException("No loans to show");
			}
			else
			return all_loans;
		}
	
	// Function for admin to add new employee
	public Message addNewEmployee(@Valid Employee e) {
		if(empRepository.findById(e.getId()).isPresent()) {
			return new Message("Employee already existss");
		}
		
		empRepository.save(e);
		return new Message("Employee successfully added");
	}
	
	// Function for admin to update employee details
	public Message updateEmployee(@Valid Employee e) {
		Optional<Employee> op = empRepository.findById(e.getId());
			
		if(op.isPresent()) {
			empRepository.save(e);
			return new Message("Employee details successfully updated");
		} else {
			return new Message("No such employee is present");
		}
	}
	
	// Function for admin to update item details
		public Message updateItem(@Valid Item item) {
			Optional<Item> op = itemRepository.findById(item.getItem_id());
				
			if(op.isPresent()) {
				itemRepository.save(item);
				return new Message("Item details successfully updated");
			} else {
				return new Message("No such item is present");
			}
		}
	
	// Function for admin to remove an item
		public Message removeItem (int item_id) {
			
			if(itemRepository.findById(item_id).isPresent()) {
				itemRepository.deleteById(item_id);
				
				return new Message("Item has been successfully deleted");
			}else {
				return new Message("Error: No such item found!");
			}
			
		}
		
	// Function for admin to remove a loan
		public Message removeLoan (int loan_id) {
			
			if(loanRepository.findById(loan_id).isPresent()) {
				loanRepository.deleteById(loan_id);
				
				return new Message("Loan has been successfully deleted");
			}else {
				return new Message("Error: No such loan found!");
			}
			
		}
	
	// Function for admin to update a loan
		public Message updateLoan(@Valid Loan loan) {
			Optional<Loan> op = loanRepository.findById(loan.getLoan_id());
			
			if(op.isPresent()) {
				Loan oldLoanData = op.get();
				
				if(oldLoanData.getDuration() != loan.getDuration()) {
					List<Integer> issue_ids = itemRepository.findIssueIdsByItemType(loan.getType().toString());
					for(int i : issue_ids) {
						Issue issue = issueRepository.findById(i).get();
						LocalDate newReturnDate = issue.getIssueDate().plusYears(loan.getDuration());
						LocalDate currReturnDate = issue.getReturnDate();
						
						if(currReturnDate.isAfter(LocalDate.now()) && newReturnDate.isAfter(currReturnDate)){
							issue.setReturnDate(newReturnDate);
							issueRepository.save(issue);
						} else if(currReturnDate.isAfter(LocalDate.now()) && newReturnDate.isBefore(currReturnDate) && newReturnDate.isAfter(LocalDate.now())) {
							issue.setReturnDate(newReturnDate);
							issueRepository.save(issue);
						}
					}				
				}	
				loanRepository.save(loan);
				return new Message("Loan details successfully updated");
			}else {
				return new Message("No such loan is present");
			}
		}
}