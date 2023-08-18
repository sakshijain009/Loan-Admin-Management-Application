package com.training.loanapplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages="com")
@SpringBootTest(classes=com.training.loanapplication.EmployeeControllerTest.class)
class LoanapplicationApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void testSaveEmployee() {
	}

}
