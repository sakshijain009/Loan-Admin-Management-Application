package com.training.loanapplication.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="employee_master")

public class Employee {
	
	@Id
	@Column(name="employee_id", length=6)
	@NotEmpty(message="Employee id cannot be empty or null")
	@NotBlank(message="Employee id cannot be blank")
	private String id;
	
	@Column(name="employee_name", length=20)
	@NotBlank(message="Employee name cannot be blank")
	@NotEmpty(message="Employee name cannot be empty or null")
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public LocalDate getDoj() {
		return doj;
	}

	public void setDoj(LocalDate doj) {
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
	@NotBlank(message="Employee department cannot be blank")
	@NotEmpty(message="Employee department cannot be empty or null")
	private String department;
	
	@Column(name="gender", length=1)
	@NotBlank(message="Employee gender cannot be blank")
	@NotEmpty(message="Employee gender cannot be empty or null")
	private String gender;
	
	@Column(name="designation", length=25)
	@NotBlank(message="Employee designation cannot be blank")
	@NotEmpty(message="Employee designation cannot be empty or null")
	private String designation;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="dob")
	@NotNull(message="DOB should not be null")
	private LocalDate dob;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="doj")
	@NotNull(message="DOJ should not be null")
	private LocalDate doj;
	
	@Column(name="password", length=20)
	@Length(min=8,max=20, message="Password length must be between 8 and 20")
	@NotBlank(message="Password cannot be blank")
	@NotEmpty(message="Password cannot be empty")
	private String password;
	
	@OnDelete(action=OnDeleteAction.CASCADE)
	@OneToMany(mappedBy="employee", fetch= FetchType.EAGER)
    private List<Issue>issue;
	
	@OnDelete(action=OnDeleteAction.CASCADE)
	@OneToMany(mappedBy="employee", fetch= FetchType.EAGER)
    private List<Card>card;

}



