package com.training.loanapplication.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name="employee_master")

public class Employee {
	
	@Id
	@Column(name="employee_id", length=6)
	private String id;
	
	@Column(name="employee_name", length=20)
	private String name;
	
	@Column(name="department", length=25)
	private String department;
	
	@Column(name="gender", length=1)
	private String gender;
	
	@Column(name="designation", length=25)
	private String designation;
	
	@Column(name="dob", length=8)
	private String dob;
	
	@Column(name="doj", length=8)
	private String doj;
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="issue_id")
    private List<Issue>issue;

}



