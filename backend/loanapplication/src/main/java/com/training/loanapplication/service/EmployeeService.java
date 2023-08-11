package com.training.loanapplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.LoginEmployee;
import com.training.loanapplication.model.LoginResult;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo;
	
	public Employee saveEmployee(Employee emp)
	{
		Employee emp_obj=empRepo.save(emp);
		return emp_obj;
	}
	
	public LoginResult validateEmployee(LoginEmployee e)
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
		LoginResult loginresult = new LoginResult();
		loginresult.setCheck_login(result);
		return loginresult;
	}
}
