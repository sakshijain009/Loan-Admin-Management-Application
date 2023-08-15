package com.training.loanapplication.service;

import java.util.List;

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
}
