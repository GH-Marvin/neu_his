package com.edu.neu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WaitingItemVO {
    @JsonProperty("registration_id")
    private String registId;
    @JsonProperty("record_id")
    private String caseNumber;
    @JsonProperty("name")
    private String realName;
    @JsonProperty("sex")
    private String gender;
    @JsonProperty("age")
    private Integer age;
}
