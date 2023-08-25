package com.training.loanapplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
import com.training.loanapplication.model.ItemCategory;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.serviceInterface.AdminServiceInterface;
import com.training.loanapplication.serviceInterface.EmployeeServiceInterface;
import com.training.loanapplication.serviceInterface.ItemServiceInterface;
import com.training.loanapplication.serviceInterface.LoanServiceInterface;

@RunWith(SpringRunner.class)
@WebMvcTest
public class LoanControllerTest {
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
	public void testGetAllTypes() throws Exception{
		List<String> all_category = new ArrayList<>();
		all_category.add("FURNITURE");
		Mockito.when(loanService.getAllTypes()).thenReturn(all_category);
		mvc.perform(get("/getAllTypes").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(all_category.size())))
		.andExpect(jsonPath("$[0]", Matchers.equalTo(all_category.get(0))));
	}
	
	@Test
	public void testGetAllLoans() throws Exception{
		List<Map<String,Object>> loan_list = new ArrayList<>();
		Map<String,Object> loan = new HashMap<String, Object>();
		loan.put("loan_id", 1);
		loan.put("duration", (short)3);
		loan.put("card_issue_date", LocalDate.now());
		loan.put("type", ItemCategory.FURNITURE);
		loan_list.add(loan);
		Mockito.when(loanService.getAllLoans("123456")).thenReturn(loan_list);
		mvc.perform(get("/getallLoans/{emp_id}","123456").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(loan_list.size())))
		.andExpect(jsonPath("$[0].loan_id", Matchers.equalTo(loan_list.get(0).get("loan_id"))));
	}
	
}
