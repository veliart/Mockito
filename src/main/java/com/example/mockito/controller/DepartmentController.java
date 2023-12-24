package com.example.mockito.controller;

import com.example.mockito.model.Employee;
import com.example.mockito.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping("{id}/employees")
	public List<Employee> getEmployees(@PathVariable("id") Integer id) {
		return departmentService.getEmployees(id);
	}

	@GetMapping("{id}/salary/sum")
	public Integer getSalarySum(@PathVariable("id") Integer id) {
		return departmentService.getSalarySum(id);
	}

	@GetMapping("{id}/salary/min")
	public Integer getEmployeeWithMinSalary(@PathVariable("id") Integer id) {
		return departmentService.getEmployeeWithMinSalary(id).getSalary();
	}

	@GetMapping("{id}/salary/max")
	public Integer getEmployeeWithMaxSalary(@PathVariable("id") Integer id) {
		return departmentService.getEmployeeWithMaxSalary(id).getSalary();
	}

	@GetMapping("employees")
	public Map<Integer, List<Employee>> getGroupedByDepartmentEmployees() {
		return departmentService.getGroupedByDepartmentEmployees();
	}
}