package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Constantitem {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  private Integer constantTypeId;
  private String constantCode;
  private String constantName;
  private Integer delmark;

}
