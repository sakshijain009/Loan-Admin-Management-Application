package com.training.loanapplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.loanapplication.model.Card;
import com.training.loanapplication.model.ItemCategory;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.ViewLoan;

import jakarta.persistence.Tuple;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
	
	@Query("SELECT l.loan_id from Loan as l where l.type = :type")
	int findIdByType(ItemCategory type);
	
//	@Query("SELECT l FROM Loan l INNER JOIN l.card as c INNER JOIN employee WHERE employee.id=?1")
//	public List<Loan> getallLoans(String emp_id);
	
	@Query(nativeQuery=true, value="SELECT l.loan_id , l.duration , l.loan_type as type , c.card_id  FROM loan_master l INNER JOIN card_details c ON l.loan_id=c.loan_id WHERE c.employee_id=\"111113\"")
	public List<Object> getallLoans(String emp_id);
}
