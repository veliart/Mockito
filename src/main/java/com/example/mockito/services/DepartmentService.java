package com.example.mockito.services;
import com.example.mockito.model.Employee;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
	Employee getEmployeeWithMaxSalary(Integer departmentId);
	Employee getEmployeeWithMinSalary(Integer departmentId);
	List<Employee> getAllEmployeesByDepartment(Integer departmentId);
	Map<Integer, List<Employee>> getAllEmployees();
}
