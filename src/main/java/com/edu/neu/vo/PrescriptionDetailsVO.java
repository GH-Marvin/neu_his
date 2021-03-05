package com.edu.neu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDetailsVO {
    @JsonProperty("id")
    private Integer pdId;
    @JsonProperty("p_id")
    private Integer prescriptionId;
    @JsonProperty("drugs_id")
    private Integer drugsId;
    @JsonProperty("name")
    private String drugsName;
    @JsonProperty("format")
    private String drugsFormat;
    @JsonProperty("price")
    private Double drugsPrice;
    @JsonProperty("use")
    private String drugsUsage;
    @JsonProperty("dosage")
    private String dosage;
    @JsonProperty("frequency")
    private String frequency;
    @JsonProperty("num")
    private Double amount;
}
