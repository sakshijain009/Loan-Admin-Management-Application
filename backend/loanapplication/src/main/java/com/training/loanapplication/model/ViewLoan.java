package com.training.loanapplication.model;

public class ViewLoan {
	private int loan_id;
	public int getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}
	public int getCard_id() {
		return card_id;
	}
	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}
	public ItemCategory getType() {
		return loan_type;
	}
	public void setType(ItemCategory type) {
		this.loan_type = type;
	}
	public short getDuration() {
		return duration;
	}
	public void setDuration(short duration) {
		this.duration = duration;
	}
	private short duration;
	private ItemCategory loan_type;
	private int card_id;
	
	
}
