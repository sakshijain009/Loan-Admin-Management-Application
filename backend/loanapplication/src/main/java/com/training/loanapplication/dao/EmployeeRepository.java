package com.training.loanapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.loanapplication.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
