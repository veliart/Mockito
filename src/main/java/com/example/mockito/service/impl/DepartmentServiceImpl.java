package com.example.mockito.service.impl;

import com.example.mockito.model.Employee;
import com.example.mockito.service.DepartmentService;
import com.example.mockito.service.EmployeeService;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final EmployeeService employeeService;

	public DepartmentServiceImpl(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public List<Employee> getEmployees(Integer departmentId) {
		return employeeService.getAllEmployees().values().stream()
				.filter(employee -> employee.getDepartment().equals(departmentId))
				.collect(Collectors.toList());
	}

	@Override
	public Integer getSalarySum(Integer departmentId) {
		return employeeService.getAllEmployees().values().stream()
				.filter(employee -> employee.getDepartment().equals(departmentId))
				.map(Employee::getSalary)
				.mapToInt(Integer::intValue)
				.sum();
	}

	@Override
	public Employee getEmployeeWithMaxSalary(Integer departmentId) {
		return employeeService.getAllEmployees().values().stream()
				.filter(employee -> employee.getDepartment().equals(departmentId))
				.max(Comparator.comparingInt(Employee::getSalary))
				.orElse(null);
	}

	@Override
	public Employee getEmployeeWithMinSalary(Integer departmentId) {
		return employeeService.getAllEmployees().values().stream()
				.filter(employee -> employee.getDepartment().equals(departmentId))
				.min(Comparator.comparingInt(Employee::getSalary))
				.orElse(null);
	}

	@Override
	public Map<Integer, List<Employee>> getGroupedByDepartmentEmployees() {
		return employeeService.getAllEmployees().values().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));
	}
}