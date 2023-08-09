package com.training.loanapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="loan_card_master")
public class Loan_card_master {
	@Column(name="card_issue_date",length=10)
	private String date;
	@Column(name="loan_id",length=10)
	private String loa_nid;
	@Column(name="employee_id",length=10)
	private String employee_id;
	
}
