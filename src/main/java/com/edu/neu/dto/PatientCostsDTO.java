package com.edu.neu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PatientCostsDTO {
    @JsonProperty("record_id")
    private String caseNumber;
    @JsonProperty("regist_id")
    private Integer registId;
    @JsonProperty("medical_id")
    private Integer medicalId;
    @JsonProperty("item_type")
    private Integer itemType;
    @JsonProperty("dept_id")
    private Integer deptId;
    @JsonProperty("create_oper_id")
    private Integer createOperId;


    @JsonProperty("name")
    private String realName;
    private String sex;
    private String age;
    private String selected_template;
    private String west_diagnosis;
    private String tcm_diagnosis;
    private String layTableRadio_5;

}
