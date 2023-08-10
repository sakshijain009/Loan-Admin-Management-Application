package com.training.loanapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.model.Employee;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository empRepo;
	public Employee saveEmployee(Employee emp)
	{
		Employee emp_obj=empRepo.save(emp);
		return emp_obj;
	}
}
