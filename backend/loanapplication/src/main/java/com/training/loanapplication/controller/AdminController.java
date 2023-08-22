package com.training.loanapplication.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.loanapplication.dto.EmployeeDTO;
import com.training.loanapplication.dto.ItemDTO;
import com.training.loanapplication.dto.LoanDTO;
import com.training.loanapplication.exception.ResourceNotFoundException;
import com.training.loanapplication.model.Admin;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.Item;
import com.training.loanapplication.model.Loan;
import com.training.loanapplication.model.Message;
import com.training.loanapplication.serviceInterface.AdminServiceInterface;
import com.training.loanapplication.serviceInterface.ItemServiceInterface;
import com.training.loanapplication.serviceInterface.LoanServiceInterface;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	AdminServiceInterface adminServiceInterface;
	
	@Autowired
	LoanServiceInterface loanServiceInterface;
	
	@Autowired
	ItemServiceInterface itemServiceInterface;
	
	@PostMapping("/login")
	public Message checkAdmin(@RequestBody @Valid Admin admin)
	{
		return adminServiceInterface.checkAdmin(admin);	
	}
	
	@PostMapping("/addUser")
	public Message addNewEmployee(@RequestBody @Valid Employee e)
	{
		return adminServiceInterface.addNewEmployee(e);	
	}
	
	@PutMapping("/updateUser")
	public Message updateEmployee(@RequestBody @Valid Employee e)
	{
		return adminServiceInterface.updateEmployee(e);	
	}
	
	@GetMapping("/getAllUser")
	public List<EmployeeDTO> getAllEmployee() throws ResourceNotFoundException
	{	
		return adminServiceInterface
				.getAllEmployee()
				.stream()
				.map(e -> modelMapper.map(e, EmployeeDTO.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/getAllLoan")
	public List<LoanDTO> getAllLoan() throws ResourceNotFoundException
	{
		return adminServiceInterface
				.getAllLoan()
				.stream()
				.map(l -> modelMapper.map(l, LoanDTO.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/getLoanById/{loan_id}")
	public LoanDTO getLoanById(@PathVariable int loan_id) throws ResourceNotFoundException
	{
		Loan loan = loanServiceInterface.getLoanById(loan_id);
		LoanDTO loanDTO = modelMapper.map(loan, LoanDTO.class);
		
		return loanDTO;
	}
	
	@PostMapping("/addLoan")
	public Message addNewLoan(@RequestBody @Valid Loan loan)
	{
		return loanServiceInterface.saveLoan(loan);	
	}
	
	@DeleteMapping("/removeEmployee/{employee_id}")
	public Message removeEmployee(@PathVariable String employee_id)
	{
		return adminServiceInterface.removeEmployee(employee_id);	
	}
	
	@DeleteMapping("/removeLoan/{loan_id}")
	public Message removeLoan(@PathVariable int loan_id)
	{
		return adminServiceInterface.removeLoan(loan_id);	
	}
	
	@PutMapping("/updateLoan")
	public Message updateLoan(@RequestBody @Valid Loan loan)
	{
		return adminServiceInterface.updateLoan(loan);	
	}
	
	@DeleteMapping("/removeItem/{item_id}")
	public Message removeItem(@PathVariable int item_id)
	{
		return adminServiceInterface.removeItem(item_id);	
	}
	
	@PutMapping("/updateItem")
	public Message updateItem(@RequestBody @Valid Item item)
	{
		return adminServiceInterface.updateItem(item);	
	}
	
	@PostMapping("/addItem")
	public Item addItem(@RequestBody @Valid Item item)
	{
		return itemServiceInterface.saveItem(item);	
	}
	
	@GetMapping("/getItemById/{item_id}")
	public ItemDTO getItemById(@PathVariable int item_id) throws ResourceNotFoundException
	{
		Item item = itemServiceInterface.getItemById(item_id);	
		ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
		
		return itemDTO;
	}
}
