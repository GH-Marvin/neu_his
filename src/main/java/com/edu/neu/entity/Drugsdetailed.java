package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Drugsdetailed {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer detailId;
  private Integer tempId;
  private Integer drugsId;
  private String drugsUsage;
  private String dosage;
  private String frequency;

}
