package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Registlevel {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  private String registCode;
  private String registName;
  private Integer sequenceNo;
  private Double registFee;
  private Integer registQuota;
  private Integer delmark;

}
