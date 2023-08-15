package com.training.loanapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.loanapplication.model.Item;
import com.training.loanapplication.service.ItemService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@PostMapping("/addItem")
	public Item saveItem(@RequestBody @Valid Item item)
	{
		return itemService.saveItem(item);
	}
	
	@GetMapping("/getAllItems")
	public List<Item> getallItems() 
	{
		return itemService.getallItems();
	}
}
