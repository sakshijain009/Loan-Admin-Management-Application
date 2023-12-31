package com.training.loanapplication.model;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="item_master")

public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="item_id")
    private Integer item_id;

	@Column(name="item_description")
    @NotEmpty(message="Description cannot be empty or null")
	@NotBlank(message="Description cannot be blank")
    private String description;
    
    @Column(name="item_status", length=1)
    @NotEmpty(message="Status cannot be empty or null")
	@NotBlank(message="Status cannot be blank")
    private String status;

	@Column(name="item_category")
	@Enumerated(EnumType.STRING)
    private ItemCategory category;
    
    @Column(name="item_value")
    @NotNull(message="Value cannot be null")
    private int value;
    
    @Column(name="item_make", length=25)
    @NotEmpty(message="Make cannot be empty or null")
	@NotBlank(message="Make cannot be blank")
    private String make;
    
    @OnDelete(action=OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
