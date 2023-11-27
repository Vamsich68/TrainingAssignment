package com.SpringTask.SpringTask.Data;

import com.SpringTask.SpringTask.FilesSystem.FileService;
import com.SpringTask.SpringTask.Models.Employee;
import com.SpringTask.SpringTask.Models.ImageData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@RestController
public class EmployeeController {
    @Autowired
    private  EmployeeService employeeService;
    @Autowired
    private FileService fileService;
    private final Logger logger=  LoggerFactory.getLogger(EmployeeController.class);
    //@RequestBody @Valid Employee employee, @RequestParam("file") MultipartFile file
    //@RequestParam("json") Employee employee,
    @PostMapping(value="/createEmployee",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Employee createEmployee(
            @RequestParam("json") Employee employee,
            @RequestParam("file") MultipartFile file
    ) throws CustomException {
        try {
            //ObjectMapper objectMapper = new ObjectMapper();
            //Employee employee = objectMapper.readValue(json, Employee.class);
            //JSONObject jsonObject= new JSONObject(json );
            //Employee employee = objectMapper.readValue(jsonObject);
            logger.info("Received employee data: {}", employee);
            fileService.uploadImageFile(file);
            logger.info("file processing successful");
            return employeeService.save(employee);
        } catch (Exception ex) {
            throw new CustomException("Employee object not created");
            //ResponseEntity.badRequest();
        }
    }

   /* @PostMapping(path = "/Create")
    public ResponseEntity<?> tempCreate(@RequestBody Employee employee){
        try{

            return  employeeService.save(employee);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }

    }*/

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
