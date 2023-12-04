package com.SpringTask.SpringTask.Models;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import java.util.Arrays;


@Entity
@Table(name = "tableImageData")
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
public class ImageData {
    @Id
    private int id;

    private String name;
    private String type;

    @Lob
    @Column(name = "image_filedata", columnDefinition = "LONGBLOB")
    private byte[] image_filedata;

    /*@OneToOne(cascade=CascadeType.ALL)
    private Employee employee;*/
    /*@OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "employee_id", insertable = false, updatable = false)
    private Integer employeeId;
*/
    public ImageData() {
    }

    public ImageData( String name, String type, byte[] imageData) {
        //this.id = id;
        this.name = name;
        this.type = type;
        this.image_filedata = imageData;
    }


    /*public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImage_filedata() {
        return image_filedata;
    }

    public void setImage_filedata(byte[] image_filedata) {
        this.image_filedata = image_filedata;
    }
}