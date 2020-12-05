package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  private String username;
  private String password;
  private String realname;
  private Integer roleId;
  private Integer doctitleId;
  private String scheduling;
  private Integer deptId;
  private Integer registlevelId;
  private Integer delmark;

}
