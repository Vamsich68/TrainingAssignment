package com.SpringTask.SpringTask.DTO;

import lombok.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String address;
    private  String email;
    private int phoneNumber;
    private int imageData;
}
