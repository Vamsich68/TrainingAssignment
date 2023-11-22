package com.SpringTask.SpringTask.Data;

import com.SpringTask.SpringTask.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private  EmployeeService employeeService;
    @PostMapping(path="/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee) throws CustomException{
        try {

            return employeeService.save(employee);
        }
        catch (Exception ex){
            throw new CustomException("Employee object not created");
        }

    }
    @GetMapping(path="/Employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAll();
    }

    @GetMapping("/Employees/{id}")
    public ResponseEntity<Employee> getCustomerById(@PathVariable int id) throws CustomException {
        Optional<Employee> employee = employeeService.getById(id);
        if (employee.isEmpty()) {
            throw new CustomException("Employee details not found");
        }
        else {
            return ResponseEntity.ok(employee.get());
        }
    }
    static class  CustomException extends Exception{
        //private  String mes;
        public CustomException(String message){
            super(message);
            //this.mes=message;

        }

    }
}
