package com.training.loanapplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.CardRepository;
import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.dao.LoanRepository;
import com.training.loanapplication.model.Card;
import com.training.loanapplication.model.Employee;
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

//	public Message applyLoan(LoanModel loanModel) {
//		Card card = new Card();
//		
//		Employee emp = empRepo.findById(loanModel.getEmployee_id()).get();
//		Loan loan = loanRepo.findByType(loanModel.getItem_category()).get();
//		
//		return null;
//	}
}
