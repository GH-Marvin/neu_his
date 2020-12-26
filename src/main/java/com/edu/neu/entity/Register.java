package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Register {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  private String caseNumber;
  private String realName;
  private Integer gender;
  private String idNumber;
  private Date birthDate;
  private Integer age;
  private String ageType;
  private String homeAddress;
  private Date visitDate;
  private String noon;
  private Integer deptId;
  private Integer userId;
  private Integer registleId;
  private Integer settleId;
  private String isBook;
  private Date registTime;
  private Integer registerId;
  private Integer visitState;

}
