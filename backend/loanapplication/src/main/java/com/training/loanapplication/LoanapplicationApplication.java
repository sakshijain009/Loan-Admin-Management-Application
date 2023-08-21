package com.training.loanapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LoanapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanapplicationApplication.class, args);
	}

}
