package com.training.loanapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="loan_card_master")

public class Loan {
	@Id
	@Column(name="loan_id",length=6)
	private Long id;
	
	@Column(name="loan_type",length=15)
	private String type;	
	
	@Column(name="duration")
	private short employee_id;
}
