package com.training.loanapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employee_master")
public class Employee_master {
	@Id
	@Column(name="employee_id", length=10)
	private String employee_id;
	@Column(name="employee_name", length=24)
	private String name;
	@Column(name="department", length=10)
	private String department;
	@Column(name="gender", length=1)
	private String gender;
	@Column(name="designation", length=10)
	private String designation;
	@Column(name="dob", length=8)
	private String dob;
	@Column(name="doj", length=8)
	private String doj;
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
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
	
	
	
}
