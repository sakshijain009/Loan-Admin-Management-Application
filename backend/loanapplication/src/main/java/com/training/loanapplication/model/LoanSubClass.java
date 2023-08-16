package com.training.loanapplication.model;

import java.time.LocalDate;

public class LoanSubClass {
	private String type;
	private short duration;
	private LocalDate date;
	private String employee_id;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public short getDuration() {
		return duration;
	}
	public void setDuration(short duration) {
		this.duration = duration;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	
}
