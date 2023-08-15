package com.training.loanapplication.service;

import java.util.List;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.loanapplication.dao.ItemRepository;
import com.training.loanapplication.model.Item;


@Service
public class ItemService {
	
	@Autowired
	ItemRepository itemRepo;
	
	public Item saveItem(Item item)
	{
		Item item_obj = itemRepo.save(item);
		return item_obj;
	}
	
	public List<Item> getallItems()
	{
		List<Item> allItems = itemRepo.findAll();
		return allItems;
	}
}
