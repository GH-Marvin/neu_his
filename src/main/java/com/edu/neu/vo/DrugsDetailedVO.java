package com.edu.neu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugsDetailedVO {
    @JsonProperty("detail_id")
    private Integer detailId;
    @JsonProperty("drugs_id")
    private Integer drugsId;
    @JsonProperty("temp_id")
    private Integer tempId;
    @JsonProperty("name")
    private String drugsName;
    @JsonProperty("format")
    private String drugsFormat;
    @JsonProperty("unit")
    private String drugsUnit;
    @JsonProperty("use")
    private String drugsUsage;
    @JsonProperty("dosage")
    private String dosage;
    @JsonProperty("frequency")
    private String frequency;
}
