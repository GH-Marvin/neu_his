package com.edu.neu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RegisterVO {
    @JsonProperty("regist_id")
    private Integer registId;
    @JsonProperty("record_id")
    private String caseNumber;
    @JsonProperty("visit_date")
    private String visitDate;
    @JsonProperty("day_type")
    private String noon;
    @JsonProperty("dept")
    private String deptName;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("regist_level_id")
    private Integer registleId;
    @JsonProperty("settle_id")
    private Integer settleId;
    @JsonProperty("book")
    private String isBook;
    @JsonProperty("regist_time")
    private String registTime;
    @JsonProperty("register_id")
    private Integer registerId;
    @JsonProperty("visit_state")
    private String visitState;
    @JsonProperty("name")
    private String realName;
    @JsonProperty("sex")
    private String gender;
    @JsonProperty("identity_id")
    private String idNumber;
    private String birthDate;
    private Integer age;
    private String ageType;
    private String homeAddress;


}
