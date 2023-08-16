package com.training.loanapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.loanapplication.model.Issue;

public interface IssueRepository  extends JpaRepository<Issue,Integer>{

}
