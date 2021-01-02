package com.edu.neu.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class RegisterForm {

    @JsonProperty("record_id")
    @NotEmpty(message = "病历号不能为空！")
    private String caseNumber;

    @JsonProperty("name")
    @NotEmpty(message = "姓名不能为空！")
    private String realName;

    @JsonProperty("age")
    @NotEmpty(message = "年龄不能为空！")
    private Integer age;

    @JsonProperty("sex")
    @NotEmpty(message = "性别不能为空！")
    private Integer gender;

    @JsonProperty("birthday")
    private Date birthDate;

    @JsonProperty("identity_id")
    private String idNumber;

    @JsonProperty("address")
    private String homeAddress;

    @JsonProperty("visit_date")
    @NotEmpty(message = "看诊日期不能为空！")
    private Date visitDate;

    @JsonProperty("dayType")
    @NotEmpty(message = "看诊午别不能为空！")
    private String noon;

    @JsonProperty("department")
    @NotEmpty(message = "看诊科室不能为空！")
    private Integer deptId;

    @JsonProperty("registLevel")
    @NotEmpty(message = "挂号级别不能为空！")
    private Integer registleId;

    @JsonProperty("doctor")
    @NotEmpty(message = "看诊医生不能为空！")
    private Integer userId;

    @JsonProperty("record")
    private String isBook;

    @JsonProperty("payment")
    private String payment;

    @JsonProperty("payType")
    @NotEmpty(message = "支付方式不能为空！")
    private Integer settleId;

    @JsonProperty("registerId")
    @NotEmpty(message = "挂号员id不能为空！")
    private Integer registerId;

}
