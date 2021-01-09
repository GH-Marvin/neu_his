package com.edu.neu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoneItemVO {

    @JsonProperty("record_id")
    private String caseNumber;
    @JsonProperty("name")
    private String realName;
    @JsonProperty("sex")
    private String gender;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("regist_id")
    private Integer registId;
    @JsonProperty("west_diagnosis")
    private String diagnosis;
    @JsonProperty("medical_id")
    private Integer medical_id;
}
