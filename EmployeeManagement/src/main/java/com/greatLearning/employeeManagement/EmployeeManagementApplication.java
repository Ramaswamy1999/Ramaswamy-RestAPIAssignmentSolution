package com.greatLearning.employeeManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="/EmployeeManagement/src/main/java/com/greatLearning/employeeManagement/controller/EmployeeController.java")
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}
}
