package com.training.loanapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.loanapplication.model.Item;

public interface ItemRepository extends JpaRepository<Item, String> {

}
