package com.training.loanapplication.model;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="loan_master")

public class Loan {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="loan_id")
	private int loan_id;
	
	public int getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(int id) {
		this.loan_id = id;
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

	@Column(name="loan_type", unique = true)
	@Enumerated(EnumType.STRING)
	private ItemCategory type;	
	
	@Column(name="duration")
	@NotNull(message="Duration cannot be null")
	private short duration;
	
	@OnDelete(action=OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
	private List<Card> card;
}
