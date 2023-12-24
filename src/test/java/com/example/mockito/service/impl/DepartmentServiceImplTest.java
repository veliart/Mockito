package com.example.mockito.service.impl;


import com.example.mockito.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

	@Mock
	private EmployeeServiceImpl employeeService;

	@InjectMocks
	private DepartmentServiceImpl departmentService;

	private final List<Employee> employees = new ArrayList<>() {{
		add(new Employee("Ivan1", "Ivanov1", 10_000, 1));
		add(new Employee("Ivan2", "Ivanov2", 20_000, 1));
		add(new Employee("Ivan3", "Ivanov3", 30_000, 2));

		add(new Employee("Ivan4", "Ivanov4", 40_000, 2));
		add(new Employee("Ivan5", "Ivanov5", 40_000, 3));

		add(new Employee("Ivan6", "Ivanov6", 10_000, 3));
	}};

	@Test
	public void shouldCorrectlyFindEmployeesByDepartmentId() {
		// given
		int departmentId = 1;
		List<Employee> expectedEmployees = new ArrayList<>() {{
			add(employees.get(0));
			add(employees.get(1));
			add(employees.get(2));
		}};

		Map<String, Employee> employeeMap = new HashMap<>();
		for (Employee employee : employees) {
			employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
		}

		when(employeeService.getAllEmployees()).thenReturn(employeeMap);

		// when
		List<Employee> actualEmployees = departmentService.getEmployees(departmentId);

		// then
		Assertions.assertEquals(expectedEmployees, actualEmployees);
	}

	@Test
	public void shouldCorrectlyCalculateSum() {
		// given
		int departmentId = 1;
		int expectedSum = 600_000;

		Map<String, Employee> employeeMap = new HashMap<>();
		for (Employee employee : employees) {
			employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
		}

		when(employeeService.getAllEmployees()).thenReturn(employeeMap);

		// when
		Integer salarySum = departmentService.getSalarySum(departmentId);

		// then
		Assertions.assertEquals(expectedSum, salarySum);
	}

	@Test
	public void shouldReturnNullWhenThereAreNoEmployeesInDepartment() {
		// given
		int departmentId = 1;

		when(employeeService.getAllEmployees()).thenReturn(Collections.emptyMap());

		// when
		Employee employee = departmentService.getEmployeeWithMinSalary(departmentId);

		// then
		Assertions.assertNull(employee);
	}

	@Test
	public void shouldCorrectlyFindMinSalary() {
		// given
		int departmentId = 1;
		Employee expectedEmployee = employees.get(0);

		Map<String, Employee> employeeMap = new HashMap<>();
		for (Employee employee : employees) {
			employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
		}

		when(employeeService.getAllEmployees()).thenReturn(employeeMap);

		// when
		Employee employee = departmentService.getEmployeeWithMinSalary(departmentId);

		// then
		Assertions.assertEquals(expectedEmployee, employee);
	}

	@Test
	public void shouldCorrectlyFindMaxSalary() {
		// given
		int departmentId = 1;
		Employee expectedEmployee = employees.get(2);

		Map<String, Employee> employeeMap = new HashMap<>();
		for (Employee employee : employees) {
			employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
		}

		when(employeeService.getAllEmployees()).thenReturn(employeeMap);

		// when
		Employee employee = departmentService.getEmployeeWithMaxSalary(departmentId);

		// then
		Assertions.assertEquals(expectedEmployee, employee);
	}

	@Test
	public void shouldCorrectlyGroupEmployeesByDepartmentId() {
		// given
		Map<String, Employee> employeeMap = new HashMap<>();
		for (Employee employee : employees) {
			employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
		}

		when(employeeService.getAllEmployees()).thenReturn(employeeMap);

		Map<Integer, List<Employee>> expectedMap = new HashMap<>() {{
			put(1, List.of(employees.get(0), employees.get(1), employees.get(2)));
			put(2, List.of(employees.get(3), employees.get(4)));
			put(3, List.of(employees.get(5)));
		}};

		// when
		Map<Integer, List<Employee>> actualMap = departmentService.getGroupedByDepartmentEmployees();

		// then
		Assertions.assertEquals(expectedMap, actualMap);
	}
}
