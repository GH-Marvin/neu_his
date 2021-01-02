package com.edu.neu.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PatientDTO {
    private String realName;
    private String idNumber;
    private String gender;
    private Date birthDate;
    private String homeAddress;
    private Integer age;
    private String ageType;
    private String caseNumber;
}
