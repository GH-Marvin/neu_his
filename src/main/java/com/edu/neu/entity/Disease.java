package com.edu.neu.entity;

import javax.persistence.*;
import lombok.Data;

@Data

public class Disease {


  private Integer id;
  private String diseaseCode;
  private String diseaseName;
  private String diseaseIcd;
  private Integer diseCategoryId;
  private Integer delmark;

}
