package com.training.loanapplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.ItemCategory;

public interface ItemRepository extends JpaRepository<Item, String> {
	
	@Query("SELECT DISTINCT i.category FROM Item as i")
	List<String> getAllCategory();
	
	@Query("SELECT DISTINCT i.make FROM Item as i WHERE i.category = :category")
	List<String> getDistinctMakesByCategory(ItemCategory category);
	
	@Query("SELECT i FROM Item as i WHERE i.category = :category AND i.make = :make")
	List<Item> getItemByMakeAndCategory(ItemCategory category, String make);
}
