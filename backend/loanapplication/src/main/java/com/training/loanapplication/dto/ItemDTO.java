package com.training.loanapplication.dto;

import java.util.List;

import com.training.loanapplication.model.Issue;
import com.training.loanapplication.model.ItemCategory;

public class ItemDTO {

	private Integer item_id;
	
	private String description;
	
	private String status;
	
	private ItemCategory category;
	
	private int value;
	
	private String make;
	
	private List<Issue> issue;

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
