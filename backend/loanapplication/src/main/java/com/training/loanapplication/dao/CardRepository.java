package com.training.loanapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.loanapplication.model.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {

}
