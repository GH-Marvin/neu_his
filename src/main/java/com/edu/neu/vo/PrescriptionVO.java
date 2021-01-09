package com.edu.neu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionVO {
    @JsonProperty("id")
    private Integer prescriptionId;
    @JsonProperty("name")
    private String prescriptionName;
    @JsonProperty("state")
    private String state;
}
