package com.training.loanapplication;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.training.loanapplication.dao.AdminRepository;
import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.dao.ItemRepository;
import com.training.loanapplication.dao.LoanRepository;
import com.training.loanapplication.model.Card;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.Issue;
import com.training.loanapplication.service.AdminService;
import com.training.loanapplication.service.EmployeeService;
import com.training.loanapplication.service.ItemService;
import com.training.loanapplication.service.LoanService;
import com.training.loanapplication.serviceInterface.AdminServiceInterface;
import com.training.loanapplication.serviceInterface.EmployeeServiceInterface;
import com.training.loanapplication.serviceInterface.ItemServiceInterface;
import com.training.loanapplication.serviceInterface.LoanServiceInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.springframework.test.web.servlet.ResultMatcher;
import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.ItemCategory;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ItemControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private EmployeeServiceInterface employeeService;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@MockBean
	private AdminServiceInterface adminService;
	
	@MockBean
	private AdminRepository adminRepository;
	
	@MockBean
	private ItemServiceInterface itemService;
	
	@MockBean
	private ItemRepository itemRepository;
	
	@MockBean
	private LoanServiceInterface loanService;
	
	@MockBean
	private LoanRepository loanRepository;
	
	ObjectMapper mapper = new ObjectMapper()
			.findAndRegisterModules()
			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	
	@Test
	public void testGetAllItems() throws Exception{
		Item item = new Item();
		item.setCategory(ItemCategory.FURNITURE);
		item.setDescription("new");
		item.setMake("wood");
		item.setStatus("A");
		item.setValue(1500);
		item.setItem_id(1);
		List<Item> allItems = new ArrayList<>();
		List<Issue> issues = new ArrayList<>();
		Issue issue = new Issue();
		issues.add(issue);
		item.setIssue(issues);
		allItems.add(item);
		
		Mockito.when(itemService.getallItems()).thenReturn(allItems);
		
		System.out.println("test method for get");
		mvc.perform(get("/getAllItems").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].value", Matchers.equalTo(item.getValue())));
	}
	
	@Test
	public void testGetAllCategory() throws Exception{
		List<String> all_category = new ArrayList<>();
		all_category.add("FURNITURE");
		Mockito.when(itemService.getAllCategory()).thenReturn(all_category);
		mvc.perform(get("/getAllCategory").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(all_category.size())))
		.andExpect(jsonPath("$[0]", Matchers.equalTo(all_category.get(0))));
		
	}
	
	@Test
	public void testgetDistinctMakesByCategory() throws Exception{
		List<String> all_make = new ArrayList<>();
		all_make.add("wood");
		ItemCategory itemcat = ItemCategory.CAR_FINANCE ;
		Mockito.when(itemService.getDistinctMakesByCategory(itemcat)).thenReturn(all_make);
		mvc.perform(get("/{type}/getAllMake",itemcat).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(all_make.size())))
		.andExpect(jsonPath("$[0]", Matchers.equalTo(all_make.get(0))));
	}
//	
	@Test
	public void testgetDistinctDescriptionByMakeAndCategory() throws Exception{
		List<String> all_desc = new ArrayList<>();
		all_desc.add("new");
		ItemCategory itemcat = ItemCategory.CAR_FINANCE;
		Mockito.when(itemService.getDistinctDescriptionByMakeAndCategory(itemcat, "steel")).thenReturn(all_desc);
		mvc.perform(get("/{category}/{make}/getAllDescriptions",itemcat,"steel").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(all_desc.size())))
		.andExpect(jsonPath("$[0]", Matchers.equalTo(all_desc.get(0))));
	}
	
	@Test
	public void testGetItemByMakeAndCategoryAndDescription() throws Exception{
		Item item = new Item();
		item.setCategory(ItemCategory.FURNITURE);
		item.setDescription("new");
		item.setMake("wood");
		item.setStatus("A");
		item.setValue(1500);
		item.setItem_id(1);
		List<Issue> issues = new ArrayList<>();
		Issue issue = new Issue();
		issues.add(issue);
		item.setIssue(issues);
		ItemCategory itemcat = ItemCategory.FURNITURE;
		Mockito.when(itemService.getItemByMakeAndCategoryAndDescription(itemcat, "wood", "new")).thenReturn(item);
		mvc.perform(get("/{category}/{make}/{description}/getItem",itemcat,"wood", "new").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.item_id", Matchers.equalTo(item.getItem_id())));
	}
	
	@Test
	public void testGetAllItemsByEmpId() throws Exception{
		List<Map<String,Object>> item_list = new ArrayList<>();
		Map<String,Object> item = new HashMap<String, Object>();
		item.put("issue_id", 1);
		item.put("item_description", "new");
		item.put("item_make", "wood");
		item.put("item_category", ItemCategory.FURNITURE);
		item.put("item_value", 1500);
		item_list.add(item);
		Mockito.when(itemService.getAllItemsByEmpId("123456")).thenReturn(item_list);
		mvc.perform(get("/viewItems/{emp_id}","123456").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(1)))
		.andExpect(jsonPath("$[0].issue_id", Matchers.equalTo(item.get("issue_id"))));
	}
}
