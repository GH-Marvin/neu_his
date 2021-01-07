package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Drugstemplate {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer tempId;
  private String name;
  private Integer userId;
  private Date creationTime;
  private Integer scopeId;
  private Integer delmark;

}
