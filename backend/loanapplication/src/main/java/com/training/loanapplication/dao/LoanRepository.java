package com.training.loanapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.loanapplication.model.ItemCategory;
import com.training.loanapplication.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
	
	@Query("SELECT l.loan_id from Loan as l where l.type = :type")
	int findIdByType(ItemCategory type);
}
