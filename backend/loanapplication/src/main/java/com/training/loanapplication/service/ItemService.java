package com.training.loanapplication.service;

import java.util.List;
import java.util.Map;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.ItemRepository;
import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.ItemCategory;

import jakarta.validation.Valid;


@Service
public class ItemService {
	
	@Autowired
	ItemRepository itemRepo;
	
	// Save an item
	public Item saveItem(@Valid Item item)
	{
		return itemRepo.save(item);
	}
		
	// Find all items
	public List<Item> getallItems() throws ResourceNotFoundException
	{
		return itemRepo.findAll();
	}
	
	// Get all item categories
	public List<String> getAllCategory() throws ResourceNotFoundException
	{
		List<String> all_categories= itemRepo.getAllCategory();
		if(all_categories.size()==0)
		{
			throw new ResourceNotFoundException("No item category available");
		}
		else
		return all_categories; 
	}
	
	// Get all makes for an item category
	public List<String> getDistinctMakesByCategory(ItemCategory category) 
	{
		return itemRepo.getDistinctMakesByCategory(category);
	}
	
	// Get all descriptions for a particular make and item category
	public List<String> getDistinctDescriptionByMakeAndCategory(ItemCategory category, String make) 
	{
		return itemRepo.getDistinctDescriptionByMakeAndCategory(category, make);
	}
	
	// Get an item by a make, category and description
	public Item getItemByMakeAndCategoryAndDescription(ItemCategory category, String make, String description) 
	{
		Item i = itemRepo.getItemByMakeAndCategoryAndDescription(category, make, description);
		return i;
	}
	
	// Get all items for an employee id
	public List<Map<String,Object>> getAllItemsByEmpId(Map<String, String> header) throws ResourceNotFoundException
	{
		System.out.println(header.get("emp_id"));
		List<Map<String,Object>> allItems=itemRepo.getAllItemsByEmpId(header.get("emp_id"));
		if(allItems.size()==0)
		{
			throw new ResourceNotFoundException("No items purchased by employee");
		}
		else
		return allItems;
	}
}
