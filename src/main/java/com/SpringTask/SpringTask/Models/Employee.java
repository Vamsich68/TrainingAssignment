package com.SpringTask.SpringTask.Models;

import com.SpringTask.SpringTask.Encryption.Encrypt;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.DataAmount;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import lombok.Data;


@Data
@Entity
@Table(name="NewEmployees")
//@Table(name="Employees")
public class Employee {
    @Id
    @GeneratedValue
    public Integer id;


    //@Size(min = 3)
    //@NotBlank(message = "enter first name")
    @Convert(converter = Encrypt.class)
    public String firstName;

    //@NotBlank(message = "enter last name")
    @Convert(converter = Encrypt.class)
    public String lastName;

    //@NotNull(message = "enter date in YYYY-MM-DD format")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate DOB;


    //@NotBlank(message = "enter details in address field")
    //@Size(max = 20)
    public String address;

    //@NotBlank(message = "email is required")
    //@Email(message = "Enter valid email")
    @Convert(converter = Encrypt.class)
    public String email;


    //@NotNull(message = "enter valid phone number")
    @Convert(converter = Encrypt.class)
    //@Size(max = 10)
    public int phoneNumber;

    @OneToOne(cascade=CascadeType.ALL)
    private  ImageData image;

    public Employee(int id,String firstName, String lastName, String DOB, String address, String email, int phoneNumber) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = LocalDate.parse(DOB, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Employee() {
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
