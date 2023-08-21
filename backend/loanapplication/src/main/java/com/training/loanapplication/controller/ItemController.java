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

import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.ItemCategory;
import com.training.loanapplication.service.ItemService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping("/getAllItems")
	public List<Item> getAllItems() throws ResourceNotFoundException
	{
		return itemService.getallItems();
	}
	
	@GetMapping("/getAllCategory")
	public List<String> getAllCategory() throws ResourceNotFoundException
	{
		return itemService.getAllCategory();
	}
	
	@GetMapping("/{category}/getAllMake") 
	public List<String> getDistinctMakesByCategory(@PathVariable ItemCategory category) throws ResourceNotFoundException
	{
		return itemService.getDistinctMakesByCategory(category);
	}
	
	@GetMapping("/{category}/{make}/getAllDescriptions")
	public List<String> getDistinctDescriptionByMakeAndCategory(@PathVariable ItemCategory category, @PathVariable String make) throws ResourceNotFoundException
	{
		return itemService.getDistinctDescriptionByMakeAndCategory(category, make);
	}
	
	@GetMapping("/{category}/{make}/{description}/getItem")
	public Item getItemByMakeAndCategoryAndDescription(@PathVariable ItemCategory category, @PathVariable String make, @PathVariable String description) throws ResourceNotFoundException
	{
		return itemService.getItemByMakeAndCategoryAndDescription(category, make, description);
	}
	
	@GetMapping("/viewItems")
	public List<Map<String,Object>> getAllItemsByEmpId(@RequestHeader Map<String, String> header) throws ResourceNotFoundException
	{
		return itemService.getAllItemsByEmpId(header);
	}
}
