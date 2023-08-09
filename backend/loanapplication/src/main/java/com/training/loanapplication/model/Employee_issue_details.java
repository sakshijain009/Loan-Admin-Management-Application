package com.training.loanapplication.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="issue_details")
public class Employee_issue_details {
	
	@Column(name="issue_date",length=10)
	private String issueDate;
	
	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String date) {
		this.issueDate = issueDate;
	}

	@Column(name="return_date",length=10)
	private String returnDate;
	
	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String date) {
		this.returnDate = returnDate;
	}
	
	@Column(name="issue_id",length=10)
	private String issueId;
	
	public String getIssueId() {
		return issueId;
	}

	public void setIssueId(String date) {
		this.issueId = issueId;
	}
}