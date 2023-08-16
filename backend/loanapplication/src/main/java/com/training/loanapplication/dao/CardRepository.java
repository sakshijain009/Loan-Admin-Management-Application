package com.training.loanapplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.loanapplication.model.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {
	
	public List<Card> findByEmployee_id(String employee_id);
}
