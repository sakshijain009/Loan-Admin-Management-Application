package com.training.loanapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="item_master")

public class Item {
	
	@Id
    @Column(name="item_id", length=6)
    private String item_id;
	
    @Column(name="item_description", length=25)
    private String decription;
    
    @Column(name="item_status", length=1)
    private String status;
    
    public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getItem_make() {
		return item_make;
	}

	public void setItem_make(String item_make) {
		this.item_make = item_make;
	}

	@Column(name="item_category", length=20)
    private String category;
    
    @Column(name="item_value")
    private int value;
    
    @Column(name="item_make", length=25)
    private String item_make;
}
