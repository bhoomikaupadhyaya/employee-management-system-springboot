package com.example.employee.controller;

import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    // Create Employee
    @PostMapping
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    // Get All Employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    // Get Employee By ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    // Update Employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @Valid @RequestBody Employee employee) {
        return service.updateEmployee(id, employee);
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return service.deleteEmployee(id);
    }

    // Find By Department
    @GetMapping("/department/{department}")
    public List<Employee> getByDepartment(@PathVariable String department) {
        return service.getByDepartment(department);
    }

    // Salary Greater Than
    @GetMapping("/highsalary")
    public List<Employee> getSalaryGreaterThan(
            @RequestParam double salary) {
        return service.getSalaryGreaterThan(salary);
    }

    // Count Employees
    @GetMapping("/count")
    public long getEmployeeCount() {
        return service.getEmployeeCount();
    }

    // Sorting
    @GetMapping("/sort")
    public List<Employee> sortEmployees(
            @RequestParam(defaultValue = "salary") String field) {
        return service.sortEmployees(field);
    }

    // Pagination
    @GetMapping("/page")
    public Page<Employee> getEmployeesPage(
            @RequestParam int page,
            @RequestParam int size) {
        return service.getEmployeesPage(page, size);
    }

    // Above Average Salary
    @GetMapping("/above-average")
    public List<Employee> getAboveAverageSalaryEmployees() {
        return service.getAboveAverageSalaryEmployees();
    }
}