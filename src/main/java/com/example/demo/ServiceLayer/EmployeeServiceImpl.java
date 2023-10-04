package com.example.demo.ServiceLayer;

import com.example.demo.Employee;
import com.example.demo.EmployeeRepository;
import com.example.demo.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);                 //to save employee ...employee repository is used by service layer
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();       //to get all the data from employee repository findall is used as a method
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee>employee=employeeRepository.findById(id);  //Optional: a container object which may or maynot contain a null value //here return type is employee
        if(employee.isPresent()){
            return employee.get();
        }else {
            throw new ResourceNotFoundException("Employee","ID",id);
        }
    }

    @Override
    public Employee updateEmployee(Long empid,Employee emp) {
        Employee oldEmp=employeeRepository.findById(empid).orElseThrow(
                ()->new ResourceNotFoundException("Employee","ID",empid));
        oldEmp.setFirstName(emp.getFirstName());                  //getter setter is used to set the data from new employee to old data
        oldEmp.setLastName(emp.getLastName());
        oldEmp.setEmail(emp.getEmail());
        employeeRepository.save(oldEmp);
        return oldEmp;
    }

    @Override
    public void deleteEmployee(Long empid) {
        employeeRepository.deleteById(empid);
    }


}

