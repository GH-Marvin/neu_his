package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Prescription {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer prescriptionId;
  private Integer medicalId;
  private Integer registId;
  private Integer userId;
  private String prescriptionName;
  private Date prescriptionTime;
  private Integer prescriptionState;

}
