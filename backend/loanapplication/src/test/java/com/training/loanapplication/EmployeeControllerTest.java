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
import com.training.loanapplication.dao.EmployeeRepository;
import com.training.loanapplication.model.Card;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.Issue;
import com.training.loanapplication.service.EmployeeService;

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
	private EmployeeService employeeService;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	ObjectMapper mapper = new ObjectMapper()
			.findAndRegisterModules()
			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	
	@Test
	public void testSaveEmployee() throws Exception{
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
		
		Mockito.when(EmployeeService.saveEmployee(ArgumentMatchers.any())).
		thenReturn(emp);
		
		String json = mapper.writeValueAsString(emp);
		MvcResult requestResult = (MvcResult) ((ResultActions) ((MockHttpServletRequestBuilder) mvc.perform(post("/api/users/addUser")))
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		String Result = requestResult.getResponse().getContentAsString();
		assertEquals(Result, "User Added");
		
		
	}
	
}
