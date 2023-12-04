package com.SpringTask.SpringTask.TestRepo;

import com.SpringTask.SpringTask.Data.EmployeeController;
import com.SpringTask.SpringTask.Data.EmployeeService;
import com.SpringTask.SpringTask.Models.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class ControllerUsingWebMvcTest {
    @MockBean
    EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test_createEmployee() throws Exception {
        Employee employee = new Employee( 1,"vamsi","ch", "warangal","aaa@gmail.com",1234056789,1);
        when(employeeService.save(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/createEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().is2xxSuccessful());
    }
}

