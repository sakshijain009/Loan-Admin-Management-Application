package com.training.loanapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="issue_details")
public class Issue {
	
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="issue_date",length=6)
	private String issueDate;
	
	public String getIssueDate() {
		return issueDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

	@Column(name="return_date",length=10)
	private String returnDate;
	
	public String getReturnDate() {
		return returnDate;
	}
	
	@Column(name="issue_id",length=10)
	private String issueId;
	
	public String getIssueId() {
		return issueId;
	}

}