package com.training.loanapplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.loanapplication.model.Issue;

public interface IssueRepository  extends JpaRepository<Issue,Integer>{

	@Query("SELECT i from Issue as i where i.item IN :item_ids")
	List<Issue> findAllWithIdInItemId(List<Integer> item_ids);

}
