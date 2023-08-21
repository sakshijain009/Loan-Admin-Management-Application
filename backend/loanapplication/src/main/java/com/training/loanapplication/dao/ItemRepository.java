package com.training.loanapplication.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.ItemCategory;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	@Query("SELECT DISTINCT i.category FROM Item as i")
	List<String> getAllCategory();
	
	@Query("SELECT DISTINCT i.make FROM Item as i WHERE i.category = :category")
	List<String> getDistinctMakesByCategory(ItemCategory category);
	
	@Query("SELECT DISTINCT i.description FROM Item as i WHERE i.category = :category AND i.make = :make")
	List<String> getDistinctDescriptionByMakeAndCategory(ItemCategory category, String make);
	
	@Query("SELECT i FROM Item as i WHERE i.category = :category AND i.make = :make AND i.description = :description")
	Item getItemByMakeAndCategoryAndDescription(ItemCategory category, String make, String description);
	
	@Query(nativeQuery=true, value="SELECT iss.issue_id, it.item_description, it.item_make, it.item_category, it.item_value FROM issue_details iss INNER JOIN item_master it ON it.item_id = iss.item_id WHERE iss.employee_id=?1")
	public List<Map<String,Object>> getAllItemsByEmpId(String emp_id);

	@Query("SELECT i from Item as i where i.category = :type")
	List<Item> findAllByType(ItemCategory type);
}
