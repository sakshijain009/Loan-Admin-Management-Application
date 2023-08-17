package com.training.loanapplication.service;

import java.util.List;
import java.util.Map;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.ItemRepository;
import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.ItemCategory;


@Service
public class ItemService {
	
	@Autowired
	ItemRepository itemRepo;
	
	public Item saveItem(Item item)
	{
		return itemRepo.save(item);
	}
	
	public List<Item> getallItems()
	{
		return itemRepo.findAll();
	}
	
	public List<String> getAllCategory()
	{
		return itemRepo.getAllCategory();
	}
	
	public List<String> getDistinctMakesByCategory(ItemCategory category)
	{
		return itemRepo.getDistinctMakesByCategory(category);
	}
	
	public List<String> getDistinctDescriptionByMakeAndCategory(ItemCategory category, String make)
	{
		return itemRepo.getDistinctDescriptionByMakeAndCategory(category, make);
	}
	
	public Item getItemByMakeAndCategoryAndDescription(ItemCategory category, String make, String description)
	{
		return itemRepo.getItemByMakeAndCategoryAndDescription(category, make, description);
	}
	
	public List<Map<String,Object>> getAllItemsByEmpId(Map<String, String> header)
	{
		System.out.println(header.get("emp_id"));
		List<Map<String,Object>> allItems=itemRepo.getAllItemsByEmpId(header.get("emp_id"));
		return allItems;
	}
}
