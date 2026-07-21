package com.example.employee.service;

import com.example.employee.entity.Employee;
import com.example.employee.exception.ResourceNotFoundException;
import com.example.employee.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    // Create Employee
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    // Get All Employees
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // Get Employee by ID
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with ID : " + id));
    }

    // Update Employee
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existing = getEmployeeById(id);

        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setSalary(employee.getSalary());
        existing.setDepartment(employee.getDepartment());

        return repository.save(existing);
    }

    // Delete Employee
    public String deleteEmployee(Long id) {

        Employee employee = getEmployeeById(id);

        repository.delete(employee);

        return "Employee deleted successfully.";
    }

    // Find by Department
    public List<Employee> getByDepartment(String department) {
        return repository.findByDepartment(department);
    }

    // Salary Greater Than
    public List<Employee> getSalaryGreaterThan(double salary) {
        return repository.findBySalaryGreaterThan(salary);
    }

    // Count Employees
    public long getEmployeeCount() {
        return repository.count();
    }

    // Sorting
    public List<Employee> sortEmployees(String field) {
        return repository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    // Pagination
    public Page<Employee> getEmployeesPage(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return repository.findAll(pageable);
    }

    // Above Average Salary
    public List<Employee> getAboveAverageSalaryEmployees() {
        return repository.findEmployeesAboveAverageSalary();
    }

}