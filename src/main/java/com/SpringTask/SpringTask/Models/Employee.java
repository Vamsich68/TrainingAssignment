package com.SpringTask.SpringTask.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import jdk.jfr.DataAmount;


import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name="Employees")
public class Employee {
    @Id
    //@GeneratedValue
    public int id;

    //@Size(min = 3)
    public String firstName;
    public String lastName;

    public Date DOB;
    //@Size(max = 20)
    public String address;
    //@Email(message = "Enter valid email")
    public String email;
    //@Size(max = 10 )
    public int phoneNumber;

    public Employee(String firstName, String lastName, Date DOB, String address, String email, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Employee() {
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

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
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
