package com.training.loanapplication.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.model.Employee;

import jakarta.validation.Valid;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	EmployeeRepository empRepo;

	
	// Method to save an employee
	public Employee saveEmployee(@Valid Employee emp)
	{
		if(empRepo.findById(emp.getId()).isPresent()) {
			return null;
		}
		
		return empRepo.save(emp);
	}

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Employee e = empRepo.findById(id).get();
  
			if(e!=null)
			{
				return new org.springframework.security.core.userdetails.User(e.getId(), e.getPassword(),
					new ArrayList<>());
			} else {
				throw new UsernameNotFoundException("User not found with username: " + id);
			}
		
		}
}
