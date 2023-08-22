package com.training.loanapplication.serviceInterface;

import java.util.List;
import java.util.Map;

import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.ItemCategory;

import jakarta.validation.Valid;

public interface ItemServiceInterface {
	public Item saveItem(@Valid Item item);
	public List<Item> getallItems() throws ResourceNotFoundException;
	public List<String> getAllCategory() throws ResourceNotFoundException;
	public Item getItemById(int item_id) throws ResourceNotFoundException;
	public List<String> getDistinctMakesByCategory(ItemCategory category);
	public List<String> getDistinctDescriptionByMakeAndCategory(ItemCategory category, String make);
	public Item getItemByMakeAndCategoryAndDescription(ItemCategory category, String make, String description);
	public List<Map<String,Object>> getAllItemsByEmpId(Map<String, String> header) throws ResourceNotFoundException;
}
