package com.edu.neu.dto;

import com.edu.neu.entity.Patientcosts;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CancelDTO {
    @JsonProperty("medical_id")
    private Integer medicalId;
    @JsonProperty("patient_costs_list")
    private List<Patientcosts> patientCostsList;
}
