package com.training.loanapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.training.loanapplication.service.CustService;

@RestController
@CrossOrigin("http://localhost:3000")
public class CustController {
	@Autowired
	CustService custService;
	
	
}
