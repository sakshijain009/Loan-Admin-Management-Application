package com.training.loanapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Card")
public class Card {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="card_id")
	private int card_id;
	
	@Column(name="employee_id")
	private int employee_id;
	
	@Column(name="loan_id")
	private int loan_id;
	
	@Column(name="card_issue_date",length=10)
	private String date;
	

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name="employee_id")
//	private List<Loan_card_master> loan_ids;
}
