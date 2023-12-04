package com.SpringTask.SpringTask.Models;

import com.SpringTask.SpringTask.Encryption.Encrypt;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.DataAmount;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name="tableEmployees")
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Table(name="Employees")
public class Employee {
    @Id
    //@GeneratedValue
    public Integer id;


    //@Size(min = 3)
    //@NotBlank(message = "enter first name")
    //@Convert(converter = Encrypt.class)
    public String firstName;

    //@NotBlank(message = "enter last name")
    //@Convert(converter = Encrypt.class)
    public String lastName;

    //@NotNull(message = "enter date in YYYY-MM-DD format")
    /*@JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate DOB;
*/

    //@NotBlank(message = "enter details in address field")
    //@Size(max = 20)
    public String address;

    //@NotBlank(message = "email is required")
    //@Email(message = "Enter valid email")
    //@Convert(converter = Encrypt.class)
    public String email;


    //@NotNull(message = "enter valid phone number")
    //@Convert(converter = Encrypt.class)
    //@Size(max = 10)
    public int phoneNumber;

   /* @OneToOne(cascade=CascadeType.ALL)
    private  ImageData image;*/

   /* @Lob
   @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
   private ImageData image;*/
    private int imageid;
}
