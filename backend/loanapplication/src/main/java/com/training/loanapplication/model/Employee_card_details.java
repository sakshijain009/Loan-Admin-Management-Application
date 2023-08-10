package com.training.loanapplication.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="card_details")
public class Employee_card_details {
	@Id
	@Column(name="card_details_id")
	private int id;
	
	@Column(name="card_issue_date",length=10)
	private String date;
	

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name="employee_id")
//	private List<Loan_card_master> loan_ids;
}
