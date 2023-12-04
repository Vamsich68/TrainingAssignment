package com.SpringTask.SpringTask.Data;

import com.SpringTask.SpringTask.Models.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

//@Service
@Repository
//@Transactional(isolation = Isolation.READ_COMMITTED)

public class EmployeeService {
    @Autowired
    private  EmployeeRepo employeeRepo;
    @Transactional

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
