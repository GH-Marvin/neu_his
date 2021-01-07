package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Prescriptiondetailed {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer pdId;
  private Integer prescriptionId;
  private Integer drugsId;
  private String drugsUsage;
  private String dosage;
  private String frequency;
  private Double amount;
  private Integer state;

}
