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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	private EmployeeService employeeService;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@MockBean
	private AdminService adminService;
	
	@MockBean
	private AdminRepository adminRepository;
	
	@MockBean
	private ItemService itemService;
	
	@MockBean
	private ItemRepository itemRepository;
	
	@MockBean
	private LoanService loanService;
	
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
}
