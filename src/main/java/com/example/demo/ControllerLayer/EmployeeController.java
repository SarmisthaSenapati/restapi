package com.example.demo.ControllerLayer;

import com.example.demo.Employee;
import com.example.demo.ServiceLayer.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")       //url given to get accessed with database
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee>saveEmployee(@RequestBody Employee employee)        //saveEmployee is method here //Response entity is used to deal with http //Requestbody is used to convert json to pojo and viceversa
    {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.CREATED);
    }
    @GetMapping
    public List<Employee>GetEmp(){

        return employeeService.getAllEmployees();
    }
    @GetMapping("{id}")

    public ResponseEntity<Employee>getEmpById(@PathVariable("id") Long empid){
     return new ResponseEntity<Employee>(employeeService.getEmployeeById(empid),HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<String>updateEmp(@PathVariable("id") Long id,@RequestBody Employee employee){         //used to convert  from json to pojo
        employeeService.updateEmployee(id,employee);                                              //to provide data to the service layer
        return new ResponseEntity<String>("Record Updated Successfully",HttpStatus.FOUND);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteEmployeeById(@PathVariable("id")Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("id: "+id+" deleted successfully",HttpStatus.OK);
    }

}
