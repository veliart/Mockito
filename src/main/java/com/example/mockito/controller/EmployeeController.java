package com.example.mockito.controller;

import com.example.mockito.model.Employee;
import com.example.mockito.services.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/add")
	public Employee add(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("salary") Integer salary,
			@RequestParam("department") Integer department)
	{
		return employeeService.add(firstName, lastName, salary, department);
	}

	@GetMapping("/remove")
	public Employee remove(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName)

	{
		return employeeService.remove(firstName, lastName);
	}
	@GetMapping
	public Map<String, Employee> getAll() {
		return employeeService.getAll();
	}

}
