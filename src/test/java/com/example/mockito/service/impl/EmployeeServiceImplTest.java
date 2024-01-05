package com.example.mockito.service.impl;

import com.example.mockito.exception.EmployeeAlreadyAddedException;
import com.example.mockito.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeServiceImplTest {

	private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

	@Test
	public void shouldThrowEmployeeAlreadyAddedExceptionWhenEmployeeIsAlreadyExisted() {
		Employee employee = new Employee("Petr", "Petrov", 100_000, 1);
		employeeService.addEmployee(
				employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment()
		);
		Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> {
			employeeService.addEmployee(
					employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment()
			);
		});
	}

	@Test
	public void shouldCorrectlyFindEmployee() {
		Employee employee = new Employee("Ivan1", "Ivanov1", 10_000, 1);
		employeeService.addEmployee(
				employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment()
		);

		Employee actualEmployee = employeeService.findEmployee(employee.getFirstName(), employee.getLastName());
		Assertions.assertEquals(employee, actualEmployee);
	}
}
