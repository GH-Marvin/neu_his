package com.edu.neu.form;

import com.edu.neu.dto.PrescriptionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PrescriptionFrom {
    @JsonProperty("medical_id")
    private Integer medicalId;
    @JsonProperty("regist_id")
    private Integer registId;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("prescription_state")
    private Integer prescriptionState;
    @JsonProperty("prescription_name")
    private String prescriptionName;
    @JsonProperty("data")
    private List<PrescriptionDTO> data;
}
