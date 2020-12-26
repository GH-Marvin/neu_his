package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Department {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  private String deptCode;
  private String deptName;
  private Integer deptCategoryId;
  private Integer deptType;
  private Integer delmark;

}
