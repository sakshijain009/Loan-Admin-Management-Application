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
import com.training.loanapplication.serviceInterface.ItemServiceInterface;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173")
public class ItemController {
	
	@Autowired
	ItemServiceInterface itemServiceInterface;
	
	@GetMapping("/getAllItems")
	public List<Item> getAllItems() throws ResourceNotFoundException
	{
		return itemServiceInterface.getallItems();
	}
	
	@GetMapping("/getAllCategory")
	public List<String> getAllCategory() throws ResourceNotFoundException
	{
		return itemServiceInterface.getAllCategory();
	}
	
	@GetMapping("/{category}/getAllMake") 
	public List<String> getDistinctMakesByCategory(@PathVariable ItemCategory category) throws ResourceNotFoundException
	{
		return itemServiceInterface.getDistinctMakesByCategory(category);
	}
	
	@GetMapping("/{category}/{make}/getAllDescriptions")
	public List<String> getDistinctDescriptionByMakeAndCategory(@PathVariable ItemCategory category, @PathVariable String make) throws ResourceNotFoundException
	{
		return itemServiceInterface.getDistinctDescriptionByMakeAndCategory(category, make);
	}
	
	@GetMapping("/{category}/{make}/{description}/getItem")
	public Item getItemByMakeAndCategoryAndDescription(@PathVariable ItemCategory category, @PathVariable String make, @PathVariable String description) throws ResourceNotFoundException
	{
		return itemServiceInterface.getItemByMakeAndCategoryAndDescription(category, make, description);
	}
	
	@GetMapping("/viewItems")
	public List<Map<String,Object>> getAllItemsByEmpId(@RequestHeader Map<String, String> header) throws ResourceNotFoundException
	{
		return itemServiceInterface.getAllItemsByEmpId(header);
	}
}
