package com.edu.neu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WestDiagnoseItemVO {
    @JsonProperty("ICD")
    private String ICD;
    private String description;
    private String time;
}
