package com.SpringTask.SpringTask.Data;

import com.SpringTask.SpringTask.FilesSystem.FileService;
import com.SpringTask.SpringTask.Models.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    //private ObjectMapper objectMapper;
    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestParam("json") String json) throws JsonProcessingException {
        try{

            ObjectMapper objectMapper = new ObjectMapper();

            Employee employee = objectMapper.readValue(json, Employee.class);
            return  employeeService.save(employee);
        }
        catch (Exception ex){
            throw new RuntimeException("object not created");
        }
    }
    @PostMapping("/addEmployee2")
    public Employee addEmployee2(@RequestBody Employee employee) throws JsonProcessingException {
        try{
            logger.info("employee data received {}", employee);
            return  employeeService.save(employee);
        }
        catch (Exception ex){
            throw new RuntimeException("object not created");
        }
    }

    @PostMapping(value="/createEmployee",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createEmployee(
            @RequestParam("json") Employee employee,
            @RequestParam("file") MultipartFile file
    ) throws CustomException {
        try {
            //ObjectMapper objectMapper = new ObjectMapper();
            //Employee employee = objectMapper.readValue(json, Employee.class);
            //JSONObject jsonObject= new JSONObject(json );
            //Employee employee = objectMapper.readValue(jsonObject);
            logger.info("Received employee data: {}", employee);
            var result=fileService.uploadImageFile(file);
            logger.info("file processing successful");
             Employee employee1= employeeService.save(employee);
             var temp=String.valueOf(employee1.getId());
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(temp);

        } catch (Exception ex) {
            //throw new CustomException("Employee object not created");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
            //ResponseEntity.badRequest();
        }
    }

    @PostMapping("/create")
    public String tempCreateEmployee(
            @RequestParam("json") String json,
            @RequestParam("file") MultipartFile file
    ) throws CustomException {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            logger.info("data received as string");
            Employee employee = objectMapper.readValue(json, Employee.class);
            logger.info(employee.toString());
            logger.info("Received employee data: {}", employee);
            int imageId=fileService.uploadImageFile(file);
            logger.info("file processing successful");
            employee.setImageid(imageId);
            logger.info(employee.toString());
            Employee savedEmployee= employeeService.save(employee);
            return "object created with id :"+ String.valueOf(savedEmployee.getId());
        }
        catch (Exception ex) {
            throw new CustomException("Employee object not created");
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
