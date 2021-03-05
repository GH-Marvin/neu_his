package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Patientcosts {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer pcId;
  private Integer registId;
  private Integer invoiceId;
  private Integer itemId;
  private Integer itemType;
  private String name;
  private Double price;
  private Double amount;
  private Integer deptId;
  private Date createTime;
  private Integer createOperId;
  private Date payTime;
  private Integer registerId;
  private Integer feeType;
  private Integer backId;
  private String caseNumber;
  private Integer state;

}
