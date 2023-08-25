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
import com.training.loanapplication.model.ItemCategory;
import com.training.loanapplication.model.LoanModel;
import com.training.loanapplication.model.Message;
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
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EmployeeControllerTest {
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
	public void testSaveEmployee() throws Exception{
//		System.out.println("hi");
		Employee emp = new Employee();
		List<Issue> issueList = new ArrayList<>();
		List<Card> cardList = new ArrayList<>();
		Issue issue = new Issue();
		Card card = new Card();
		issueList.add(issue);
		cardList.add(card);
		
		emp.setId("123456");
		emp.setName("ABC");
		emp.setPassword("12345678");
		emp.setDepartment("DepA");
		emp.setDesignation("Des1");
		emp.setDob(LocalDate.now());
		emp.setDoj(LocalDate.now());
		emp.setGender("M");
		emp.setIssue(issueList);
		emp.setCard(cardList);
		
		Mockito.when(employeeService.saveEmployee(ArgumentMatchers.any())).
		thenReturn(emp);
		System.out.println("hi");
		String json = mapper.writeValueAsString(emp);
		
		mvc.perform(post("/api/users/addUser").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
		.content(json).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", Matchers.equalToIgnoringCase(emp.getName())));
		
	}
	
	@Test 
	public void testGetEmployeeProfile() throws Exception{
		Employee emp = new Employee();
		List<Issue> issueList = new ArrayList<>();
		List<Card> cardList = new ArrayList<>();
		Issue issue = new Issue();
		Card card = new Card();
		issueList.add(issue);
		cardList.add(card);
		
		emp.setId("123456");
		emp.setName("ABC");
		emp.setPassword("12345678");
		emp.setDepartment("DepA");
		emp.setDesignation("Des1");
		emp.setDob(LocalDate.now());
		emp.setDoj(LocalDate.now());
		emp.setGender("M");
		emp.setIssue(issueList);
		emp.setCard(cardList);
		
		Mockito.when(employeeService.getEmployeeProfile(ArgumentMatchers.any())).thenReturn(emp);
		mvc.perform(get("/api/users/profile/{emp_id}","123456").contentType(MediaType.APPLICATION_JSON))
		
		.andExpect(status().isOk())
//		.andExpect(jsonPath("$", Matchers.hasSize(1)))
		.andExpect(jsonPath("$.id", Matchers.equalTo(emp.getId())));
	}
	
	@Test
	public void testValidateEmployee() throws Exception{
		Employee emp = new Employee();
		List<Issue> issueList = new ArrayList<>();
		List<Card> cardList = new ArrayList<>();
		Issue issue = new Issue();
		Card card = new Card();
		issueList.add(issue);
		cardList.add(card);
		
		emp.setId("123456");
		emp.setName("ABC");
		emp.setPassword("12345678");
		emp.setDepartment("DepA");
		emp.setDesignation("Des1");
		emp.setDob(LocalDate.now());
		emp.setDoj(LocalDate.now());
		emp.setGender("M");
		emp.setIssue(issueList);
		emp.setCard(cardList);
		
		Message mes = new Message("Login success");
		Mockito.when(employeeService.validateEmployee(ArgumentMatchers.any())).
		thenReturn(mes);
		String json = mapper.writeValueAsString(emp);
		
		mvc.perform(post("/api/users/checkLogin").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
		.content(json).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message", Matchers.equalToIgnoringCase(mes.getMessage())));
	}
	@Test
	public void testApplyLoan() throws Exception{
		LoanModel loan_model = new LoanModel();
		loan_model.setEmployee_id("123456");
		loan_model.setItem_value(100);
		loan_model.setItem_category(ItemCategory.CAR_FINANCE);
		loan_model.setItem_description("new");
		loan_model.setItem_make("steel");
		Message mes = new Message("Sucess");
		Mockito.when(employeeService.applyLoan(ArgumentMatchers.any())).
		thenReturn(mes);
		System.out.println("hi");
		String json = mapper.writeValueAsString(loan_model);
		
		mvc.perform(post("/api/users/applyLoan").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
		.content(json).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message", Matchers.equalToIgnoringCase(mes.getMessage())));
	}
	
}
