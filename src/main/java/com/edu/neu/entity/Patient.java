package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Patient {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private String patientId;
  private String realName;
  private String idNumber;
  private Integer gender;
  private Date birthDate;
  private String homeAddress;
  private Integer age;
  private String ageType;
  private String caseNumber;

}
