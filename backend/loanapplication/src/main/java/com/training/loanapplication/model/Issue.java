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
	
	@Column(name="return_date", length=8)
	private String returnDate;
	
    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;
    
    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

}