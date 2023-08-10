package com.training.loanapplication.model;

//import java.util.List;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="issue_details")
public class Issue {
	
	@Id
	@Column(name="issue_id", length=6)
	private String issue_id;
	
	@Column(name="issue_date",length=8)
	private String issueDate;
	
	public String getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(String issue_id) {
		this.issue_id = issue_id;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Column(name="return_date", length=8)
	private String returnDate;
	
    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;
    
    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

}