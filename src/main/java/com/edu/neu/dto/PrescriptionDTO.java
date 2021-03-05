package com.edu.neu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PrescriptionDTO {
    @JsonProperty("pid")
    private Integer prescriptionId;
    @JsonProperty("drugs_id")
    private Integer drugsId;
    @JsonProperty("use")
    private String drugsUsage;
    @JsonProperty("dosage")
    private String dosage;
    @JsonProperty("frequency")
    private String frequency;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("state")
    private Integer state;

}
