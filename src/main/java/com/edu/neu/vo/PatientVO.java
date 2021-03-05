package com.edu.neu.vo;

import lombok.Data;

import java.util.Date;

@Data
public class PatientVO {
    private String realName;
    private Integer gender;
    private String idNumber;
    private String birthDate;
    private Integer age;
    private String homeAddress;
}
