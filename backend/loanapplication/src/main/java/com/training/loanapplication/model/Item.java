package com.training.loanapplication.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="item_master")

public class Item {
	
	@Id
	@GeneratedValue
    private Integer item_id;
	
    @Column(name="item_description")
    private String description;
    
    @Column(name="item_status", length=1)
    private String status;

	@Column(name="item_category")
	@Enumerated(EnumType.STRING)
    private ItemCategory category;
    
    @Column(name="item_value")
    private int value;
    
    @Column(name="item_make", length=25)
    private String make;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="issue_id")
    private List<Issue>issue;

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ItemCategory getCategory() {
		return category;
	}

	public void setCategory(ItemCategory category) {
		this.category = category;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public List<Issue> getIssue() {
		return issue;
	}

	public void setIssue(List<Issue> issue) {
		this.issue = issue;
	}
    
}
