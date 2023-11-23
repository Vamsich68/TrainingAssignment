package com.SpringTask.SpringTask.Data;

import com.SpringTask.SpringTask.Models.Employee;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private  EmployeeService employeeService;
    @PostMapping(path="/createEmployee")
    public Employee createEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult) throws CustomException{
        if(bindingResult.hasErrors()){
            return (Employee) bindingResult.getAllErrors();
        }
        try {

            return employeeService.save(employee);
        }
        catch (Exception ex){
            throw new CustomException("Employee object not created");
        }

    }
    @GetMapping(path="/Employees")
    public List<Employee> getAllEmployees() throws CustomException {

        //return employeeService.getAll();
        List<Employee> employeesList= employeeService.getAll();
        if(employeesList.isEmpty()){
            //return null;
            throw new CustomException("This table doesn't contain any data");
        }
        else{
            return employeesList;
        }
    }

    @GetMapping("/Employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) throws CustomException {
        Optional<Employee> employee = employeeService.getById(id);
        if (employee.isEmpty()) {
            throw new CustomException("Employee details not found");
        }
        else {
            return ResponseEntity.ok(employee.get());
        }
    }



    public static class  CustomException extends Exception{
        //private  String mes;
        public CustomException(String message){
            super(message);
            //this.mes=message;

        }

    }
}
