package com.edu.neu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiseaseVO {
    private Integer id;

    @JsonProperty("ICD")
    private String diseaseIcd;

    @JsonProperty("code")
    private String diseaseCode;

    @JsonProperty("description")
    private String diseaseName;
}
