package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Drugs {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer drugsId;
  private String drugsCode;
  private String drugsName;
  private String drugsFormat;
  private String drugsUnit;
  private String manufacturer;
  private Integer drugsDosageId;
  private Integer drugsTypeId;
  private Double drugsPrice;
  private String mnemonicCode;
  private Date creationDate;
  private Date lastUpdateDate;
  private Integer delmark;

}
