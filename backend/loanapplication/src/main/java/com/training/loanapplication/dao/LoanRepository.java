package com.training.loanapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.loanapplication.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
//	Loan findByType();
}
