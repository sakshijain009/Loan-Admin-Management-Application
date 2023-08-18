package com.training.loanapplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.training.loanapplication.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
	@Query("SELECT distinct e FROM Employee e INNER JOIN e.card WHERE e.id = :emp_id")
	List<Employee> findCardByEmployeeId(String emp_id);
	
//	Employee deleteById(String id);
}
