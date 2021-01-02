package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Medicaldisease {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  private Integer medicalId;
  private Integer registId;
  private Integer diseaseId;
  private Integer diagnoseType;
  private Date siskDate;
  private Integer diagnoseCate;

}
