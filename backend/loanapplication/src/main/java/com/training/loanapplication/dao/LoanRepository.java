package com.training.loanapplication.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.loanapplication.model.ItemCategory;
import com.training.loanapplication.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
	
	@Query("SELECT l.loan_id from Loan as l where l.type = :type")
	int findIdByType(ItemCategory type);
	
	@Query(nativeQuery=true, value="SELECT l.loan_id , l.duration , l.loan_type as type , c.card_issue_date FROM loan_master l INNER JOIN card_details c ON l.loan_id=c.loan_id WHERE c.employee_id=?1")
	public List<Map<String,Object>> getallLoans(String emp_id);
	
	@Query("SELECT l from Loan as l where l.type = :itemCategory")
	Loan findByType(ItemCategory itemCategory);
	
	@Query("SELECT DISTINCT l.type FROM Loan as l")
	List<String> getAllTypes();
	
}
