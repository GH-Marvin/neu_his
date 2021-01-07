package com.edu.neu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugsVO {
    @JsonProperty("drugs_id")
    private Integer drugsId;
    @JsonProperty("code")
    private String drugsCode;
    @JsonProperty("name")
    private String drugsName;
    @JsonProperty("format")
    private String drugsFormat;
    @JsonProperty("price")
    private Double drugsPrice;
    @JsonProperty("unit")
    private String drugsUnit;
    @JsonProperty("dosage_form")
    private String drugsDosage;
    @JsonProperty("type")
    private String drugsType;
}
