package com.example.demo.ServiceLayer;

import com.example.demo.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();    //list is used to hold multiple employee data
      Employee getEmployeeById(Long id);
      Employee updateEmployee(Long empid,Employee emp);
      void deleteEmployee(Long empid);
}
