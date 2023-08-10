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
public class card {
	
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
