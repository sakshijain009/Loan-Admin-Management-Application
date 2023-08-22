package com.training.loanapplication.dto;

import java.util.List;

import com.training.loanapplication.model.Card;
import com.training.loanapplication.model.ItemCategory;

public class LoanDTO {
	
	private int loan_id;
	
	private ItemCategory type;
	
	private short duration;
	
	private List<Card> card;

	public int getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}

	public ItemCategory getType() {
		return type;
	}

	public void setType(ItemCategory type) {
		this.type = type;
	}

	public short getDuration() {
		return duration;
	}

	public void setDuration(short duration) {
		this.duration = duration;
	}

	public List<Card> getCard() {
		return card;
	}

	public void setCard(List<Card> card) {
		this.card = card;
	}
	
}
