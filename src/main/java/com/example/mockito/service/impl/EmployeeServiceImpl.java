package com.example.mockito.service.impl;

import com.example.mockito.exception.EmployeeAlreadyAddedException;
import com.example.mockito.exception.EmployeeNotFoundException;
import com.example.mockito.exception.EmployeeStorageIsFullException;
import com.example.mockito.model.Employee;
import com.example.mockito.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final int EMPLOYEES_STORAGE_SIZE = 5;
	private final Map<String, Employee> employees = new HashMap<>();

	@PostConstruct
	public void init() {
		addEmployee("Ivan1", "Ivanov1", 10_000, 1);
		addEmployee("Ivan2", "Ivanov2", 20_000, 1);
		addEmployee("Ivan3", "Ivanov3", 30_000, 2);
		addEmployee("Ivan4", "Ivanov4", 40_000, 2);
		addEmployee("Ivan5", "Ivanov5", 40_000, 3);
	}

	@Override
	public Employee addEmployee(String firstName, String lastName, Integer salary, Integer department) {
		String employeeKey = getEmployeeKey(firstName, lastName);

		if (employees.containsKey(employeeKey)) {
			throw new EmployeeAlreadyAddedException("Сотрудник уже есть в базе");
		}

		if (employees.size() == EMPLOYEES_STORAGE_SIZE) {
			throw new EmployeeStorageIsFullException("База данных сотрудников заполнена");
		}

		employees.put(
				employeeKey,
				new Employee(firstName, lastName, salary, department)
		);

		return employees.get(employeeKey);
	}

	@Override
	public void removeEmployee(String firstName, String lastName) {
		String employeeKey = getEmployeeKey(firstName, lastName);

		if (!employees.containsKey(employeeKey)) {
			throw new EmployeeNotFoundException("Данный сотрудник отсутствует в базе данных");
		}

		employees.remove(employeeKey);
	}

	@Override
	public Employee findEmployee(String firstName, String lastName) {
		String employeeKey = getEmployeeKey(firstName, lastName);

		if (!employees.containsKey(employeeKey)) {
			throw new EmployeeNotFoundException("Данный сотрудник отсутствует в базе данных");
		}

		return employees.get(employeeKey);
	}

	@Override
	public Map<String, Employee> getAllEmployees() {
		return employees;
	}

	private String getEmployeeKey(String firstName, String lastName) {
		return firstName + lastName;
	}
}