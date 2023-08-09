package com.training.loanapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="item_master")
public class Item_master {
    @Id
    @Column(name="item_id")
    private int item_id;
    @Column(name="item_description", length=30)
    private String decription;
    @Column(name="item_status", length=8)
    private String status;
    @Column(name="item_category", length=10)
    private String category;
    @Column(name="item_value")
    private int value;
    @Column(name="item_make", length=10)
    private String item_make;
}
