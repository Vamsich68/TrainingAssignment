package com.SpringTask.SpringTask.Data;

import com.SpringTask.SpringTask.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

//@Service
@Repository
public class EmployeeService {
    @Autowired
    private  EmployeeRepo employeeRepo;

    public Employee save(Employee employee){
        return employeeRepo.save(employee);
    }
    public List<Employee> getAll(){
        return employeeRepo.findAll();
    }


    public Optional<Employee> getById(int id) {
        Optional<Employee> employees = employeeRepo.findById(id);
        return employees;
    }


}
