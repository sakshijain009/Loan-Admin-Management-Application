package com.training.loanapplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.loanapplication.model.Card;
import com.training.loanapplication.model.ItemCategory;
import com.training.loanapplication.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
	
	@Query("SELECT l.loan_id from Loan as l where l.type = :type")
	int findIdByType(ItemCategory type);
	
	@Query("SELECT l FROM Loan l INNER JOIN l.card as c WHERE c.employee.id=?1")
	public List<Object> getallLoans(String emp_id);
}
