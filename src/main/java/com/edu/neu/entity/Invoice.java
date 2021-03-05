package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Invoice {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer invoiceId;
  private String invoiceNum;
  private Double money;
  private Integer state;
  private Date creationTime;
  private Integer userId;
  private Integer feeType;
  private String back;
  private Integer dailyState;

}
