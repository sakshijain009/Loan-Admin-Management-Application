package com.training.loanapplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.training.loanapplication.dao.AdminRepository;
import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.dao.ItemRepository;
import com.training.loanapplication.dao.LoanRepository;
import com.training.loanapplication.model.Card;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.Issue;
import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.ItemCategory;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.Message;
import com.training.loanapplication.service.AdminService;
import com.training.loanapplication.service.EmployeeService;
import com.training.loanapplication.service.ItemService;
import com.training.loanapplication.service.LoanService;
import com.training.loanapplication.serviceInterface.AdminServiceInterface;
import com.training.loanapplication.serviceInterface.EmployeeServiceInterface;
import com.training.loanapplication.serviceInterface.ItemServiceInterface;
import com.training.loanapplication.serviceInterface.LoanServiceInterface;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AdminControllerTest {
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
	public void testAddNewEmployee() throws Exception{
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
		
		Message mes = new Message("Employee successfully added");
		Mockito.when(adminService.addNewEmployee(ArgumentMatchers.any())).
		thenReturn(mes);
		String json = mapper.writeValueAsString(emp);
		
		mvc.perform(post("/api/admin/addUser").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
		.content(json).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message", Matchers.equalToIgnoringCase(mes.getMessage())));
	}
	
	@Test
	public void testUpdateUser() throws Exception{
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
		Message mes = new Message("Employee details successfully updated");
		Mockito.when(adminService.updateEmployee(ArgumentMatchers.any())).thenReturn(mes);
		String json = mapper.writeValueAsString(emp);
		
		mvc.perform(put("/api/admin/updateUser").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
		.content(json).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message", Matchers.equalToIgnoringCase(mes.getMessage())));
	}
	
	@Test
	public void testRemoveEmployee() throws Exception{
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
		Message mes = new Message("Employee has been successfully deleted");
		Mockito.when(adminService.removeEmployee(ArgumentMatchers.any())).thenReturn(mes);
//		String json = mapper.writeValueAsString(emp);
		
		mvc.perform(delete("/api/admin/removeEmployee/{employee_id}",1).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message", Matchers.equalToIgnoringCase(mes.getMessage())));
	}
	
	@Test
	public void testGetAllEmployee() throws Exception{
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
		List<Employee> emp_list = new ArrayList<>();
		emp_list.add(emp);
		
		Mockito.when(adminService.getAllEmployee()).thenReturn(emp_list);
		mvc.perform(get("/api/admin/getAllUser").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(1)))
		.andExpect(jsonPath("$[0].id", Matchers.equalTo(emp.getId())));
	}
	
	@Test
	public void testGetAllLoan() throws Exception{
		Loan loan = new Loan();
		List<Card> card_list = new ArrayList<>();
		Card card = new Card();
		card_list.add(card);
		loan.setDuration((short)3);
		loan.setLoan_id(1);
		loan.setType(ItemCategory.FURNITURE);
		loan.setCard(card_list);
		List<Loan> loan_list = new ArrayList<>();
		loan_list.add(loan);
		Mockito.when(adminService.getAllLoan()).thenReturn(loan_list);
		mvc.perform(get("/api/admin/getAllLoan").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(1)))
		.andExpect(jsonPath("$[0].loan_id", Matchers.equalTo(loan.getLoan_id())));
	}
	
	@Test
	public void testGetLoanById() throws Exception{
		Loan loan = new Loan();
		List<Card> card_list = new ArrayList<>();
		Card card = new Card();
		card_list.add(card);
		loan.setDuration((short)3);
		loan.setLoan_id(1);
		loan.setType(ItemCategory.FURNITURE);
		loan.setCard(card_list);
//		List<Loan> loan_list = new ArrayList<>();
//		loan_list.add(loan);
		Mockito.when(loanService.getLoanById(ArgumentMatchers.anyInt())).thenReturn(loan);
		mvc.perform(get("/api/admin/getLoanById/{loan_id}",1).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.loan_id", Matchers.equalTo(loan.getLoan_id())));
	}
	
	@Test
	public void testAddNewLoan() throws Exception{
		Loan loan = new Loan();
		List<Card> card_list = new ArrayList<>();
		Card card = new Card();
		card_list.add(card);
		loan.setDuration((short)3);
		loan.setLoan_id(1);
		loan.setType(ItemCategory.FURNITURE);
		loan.setCard(card_list);
		Message mes=new Message("Loan has been added successfully");
		String json = mapper.writeValueAsString(loan);
		Mockito.when(loanService.saveLoan(ArgumentMatchers.any())).thenReturn(mes);
		mvc.perform(post("/api/admin/addLoan").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message", Matchers.equalTo(mes.getMessage())));
	}
	
	@Test
	public void testRemoveLoan() throws Exception{
		Loan loan = new Loan();
		List<Card> card_list = new ArrayList<>();
		Card card = new Card();
		card_list.add(card);
		loan.setDuration((short)3);
		loan.setLoan_id(1);
		loan.setType(ItemCategory.FURNITURE);
		loan.setCard(card_list);
		Message mes = new Message("Loan has been successfully deleted");
		Mockito.when(adminService.removeLoan(ArgumentMatchers.anyInt())).thenReturn(mes);
//		String json = mapper.writeValueAsString(emp);
		
		mvc.perform(delete("/api/admin/removeLoan/{loan_id}",1).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message", Matchers.equalToIgnoringCase(mes.getMessage())));
	}
	
	@Test
	public void testUpdateLoan() throws Exception{
		Loan loan = new Loan();
		List<Card> card_list = new ArrayList<>();
		Card card = new Card();
		card_list.add(card);
		loan.setDuration((short)3);
		loan.setLoan_id(1);
		loan.setType(ItemCategory.FURNITURE);
		loan.setCard(card_list);
		Message mes=new Message("Loan details successfully updated");
		String json = mapper.writeValueAsString(loan);
		Mockito.when(adminService.updateLoan(ArgumentMatchers.any())).thenReturn(mes);
		mvc.perform(put("/api/admin/updateLoan").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message", Matchers.equalTo(mes.getMessage())));
	}
	
	@Test
	public void testAddItem() throws Exception{
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
		String json = mapper.writeValueAsString(item);
		Mockito.when(itemService.saveItem(ArgumentMatchers.any())).thenReturn(item);
		mvc.perform(post("/api/admin/addItem").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.item_id", Matchers.equalTo(item.getItem_id())));
	}
	
	@Test
	public void TestUpdateItem() throws Exception{
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
		Message mes=new Message("Loan details successfully updated");
		String json = mapper.writeValueAsString(item);
		Mockito.when(adminService.updateItem(ArgumentMatchers.any())).thenReturn(mes);
		mvc.perform(put("/api/admin/updateItem").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message", Matchers.equalTo(mes.getMessage())));
	}
	
	@Test
	public void testRemoveItem() throws Exception{
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
		Message mes = new Message("Item has been successfully deleted");
		Mockito.when(adminService.removeItem(ArgumentMatchers.anyInt())).thenReturn(mes);
		mvc.perform(delete("/api/admin/removeItem/{item_id}",1).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message", Matchers.equalToIgnoringCase(mes.getMessage())));
	}
	
	@Test 
	public void testGetItemById() throws Exception{
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
		Mockito.when(itemService.getItemById(ArgumentMatchers.anyInt())).thenReturn(item);
		mvc.perform(get("/api/admin/getItemById/{item_id}",1).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.item_id", Matchers.equalTo(item.getItem_id())));
	}
	
}
