package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Medicalrecord {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  private String caseNumber;
  private Integer registId;
  private String readme;
  private String present;
  private String presentTreat;
  private String history;
  private String allergy;
  private String physique;
  private String proposal;
  private String careful;
  private String checkResult;
  private String diagnosis;
  private String handling;
  private Integer caseState;

}
