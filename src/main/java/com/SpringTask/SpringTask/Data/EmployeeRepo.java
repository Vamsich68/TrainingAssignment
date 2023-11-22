package com.SpringTask.SpringTask.Data;

import com.SpringTask.SpringTask.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

}
