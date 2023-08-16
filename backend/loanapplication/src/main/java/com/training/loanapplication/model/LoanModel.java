package com.training.loanapplication.model;

public class LoanModel {
	private String employee_id;
	private ItemCategory item_category;
	private String item_make;
	private String item_description;
	private int item_value;
	
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public ItemCategory getItem_category() {
		return item_category;
	}
	public void setItem_category(ItemCategory item_category) {
		this.item_category = item_category;
	}
	public String getItem_make() {
		return item_make;
	}
	public void setItem_make(String item_make) {
		this.item_make = item_make;
	}
	public String getItem_description() {
		return item_description;
	}
	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}
	public int getItem_value() {
		return item_value;
	}
	public void setItem_value(int item_value) {
		this.item_value = item_value;
	}
	
}
