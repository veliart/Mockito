package com.example.mockito.model;

import java.util.Objects;

public class Employee {

	private final String firstName;
	private final String lastName;

	private final Integer salary;
	private final Integer department;

	public Employee(String firstName, String lastName, Integer salary, Integer department) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.department = department;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Integer getSalary() {
		return salary;
	}

	public Integer getDepartment() {
		return department;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(getFirstName(), employee.getFirstName()) && Objects.equals(getLastName(), employee.getLastName()) && Objects.equals(getSalary(), employee.getSalary()) && Objects.equals(getDepartment(), employee.getDepartment());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFirstName(), getLastName(), getSalary(), getDepartment());
	}

	@Override
	public String toString() {
		return "Employee{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", salary=" + salary +
				", department=" + department +
				'}';
	}
}