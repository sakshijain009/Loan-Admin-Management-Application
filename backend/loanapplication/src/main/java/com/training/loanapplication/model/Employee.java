package com.training.loanapplication.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="employee_master")

public class Employee {
	
	@Id
	@Column(name="employee_id", length=6)
	@NotEmpty(message="Employee id cannot be empty or null")
	private String id;
	
	@Column(name="employee_name", length=20)
	@NotBlank(message="Employee name cannot be blank")
	private String name;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Issue> getIssue() {
		return issue;
	}

	public void setIssue(List<Issue> issue) {
		this.issue = issue;
	}

	public List<Card> getCard() {
		return card;
	}

	public void setCard(List<Card> card) {
		this.card = card;
	}

	@Column(name="department", length=25)
	private String department;
	
	@Column(name="gender", length=1)
	private String gender;
	
	@Column(name="designation", length=25)
	private String designation;
	
//	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="dob")
	private String dob;
	
//	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="doj")
	private String doj;
	
	@Column(name="password", length=20)
	@Length(min=8,max=20, message="Password length must be between 8 and 20")
	@NotBlank(message="Password cannot be empty")
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="issue_id")
    private List<Issue>issue;
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="card_id")
    private List<Card>card;

}



