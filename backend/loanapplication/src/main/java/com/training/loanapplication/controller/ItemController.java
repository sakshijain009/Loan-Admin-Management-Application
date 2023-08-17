package com.training.loanapplication.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.ItemCategory;
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
	public List<Item> getAllItems() 
	{
		return itemService.getallItems();
	}
	
	@GetMapping("/getAllCategory")
	public List<String> getAllCategory() 
	{
		return itemService.getAllCategory();
	}
	
	@GetMapping("/{category}/getAllMake")
	public List<String> getDistinctMakesByCategory(@PathVariable ItemCategory category) 
	{
		return itemService.getDistinctMakesByCategory(category);
	}
	
	@GetMapping("/{category}/{make}/getAllDescriptions")
	public List<String> getDistinctDescriptionByMakeAndCategory(@PathVariable ItemCategory category, @PathVariable String make) 
	{
		return itemService.getDistinctDescriptionByMakeAndCategory(category, make);
	}
	
	@GetMapping("/{category}/{make}/{description}/getItem")
	public Item getItemByMakeAndCategoryAndDescription(@PathVariable ItemCategory category, @PathVariable String make, @PathVariable String description) 
	{
		return itemService.getItemByMakeAndCategoryAndDescription(category, make, description);
	}
	
	@GetMapping("/viewItems")
	public List<Object> getAllItemsByEmpId(@RequestHeader Map<String, String> header) 
	{
		return itemService.getAllItemsByEmpId(header);
	}
}
