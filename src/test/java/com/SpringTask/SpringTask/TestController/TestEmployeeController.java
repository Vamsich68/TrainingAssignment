package com.SpringTask.SpringTask.TestController;

import com.SpringTask.SpringTask.Data.EmployeeController;
import com.SpringTask.SpringTask.Data.EmployeeService;
import com.SpringTask.SpringTask.Models.Employee;
import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class TestEmployeeController {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    EmployeeController employeeController;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

   // @Test
    public void TestCreateEmployee() throws EmployeeController.CustomException {
        //Employee employee1 = new Employee(51,"vamsi","ch","warangal","aaa@gmail.com",1234056789);
        //Employee employee2 = new Employee(52, "vamsi","ch","warangal","aaa@gmail.com",1234056789);
        //when(employeeService.save(any(Employee.class))).thenReturn(employee2);
        //Employee result= employeeController.createEmployee(employee1);
        //Employee result2=employeeController.createEmployee(employee2);
        //assertNotNull(result);
        //assertNotNull(result2);
        //verify(employeeService, times(1)).save(employee2);


    }
    @Test
    public void TestGetAllEmployees() throws EmployeeController.CustomException {
        List<Employee> employees= employeeService.getAll();
        assertNotNull(employees.size());
        List<Employee> employees1 = employeeController.getAllEmployees();
        assertNotNull(employees1);
        assertEquals(employees.size(),employees1.size());
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void TestGetEmployeeById() throws EmployeeController.CustomException {
        /*try{
            Employee employee= new Employee(1,"a","a","1999-12-12T00:00:00","warangal","aaa@gmail.com",1234056789);

            when(employeeService.getById(2)).thenReturn(Optional.of(employee));
            ResponseEntity<Employee> result = employeeController.getEmployeeById(3);
            assertNull(result);
        }
        catch (NullPointerException e){

        }*/
        Employee employee= new Employee(1,"a","a","warangal","aaa@gmail.com",1234056789,1);

        when(employeeService.getById(2)).thenReturn(Optional.of(employee));
        ResponseEntity<Employee> result = employeeController.getEmployeeById(3);
        assertNull(result);
        //ResponseEntity<Employee> result = employeeController.getEmployeeById(2);
        //assertNotNull(result);
    }
}

