package com.training.loanapplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.loanapplication.model.ItemCategory;
import com.training.loanapplication.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
	
	@Query("SELECT l.loan_id from Loan as l where l.type = :type")
	int findIdByType(ItemCategory type);
	
//	@Query("SELECT distinct l FROM Loan as l INNER JOIN l.card WHERE l.loan_id = :emp_id")
//	List<Loan> findLoanByEmployeeId(String emp_id);
}
