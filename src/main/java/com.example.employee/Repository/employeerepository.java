package com.example.employee.repository;

import com.example.employee.entity.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface employeeRepository extends JpaRepository<Employee, Long> {

    // Find by Department
    List<Employee> findByDepartment(String department);

    // Salary greater than given value
    List<Employee> findBySalaryGreaterThan(double salary);

    // Sorting
    List<Employee> findAll(Sort sort);

    // JPQL Query - Salary Above Average
    @Query("SELECT e FROM Employee e WHERE e.salary > (SELECT AVG(emp.salary) FROM Employee emp)")
    List<Employee> findEmployeesAboveAverageSalary();
}