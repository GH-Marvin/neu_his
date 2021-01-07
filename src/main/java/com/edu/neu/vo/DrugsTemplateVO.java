package com.edu.neu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugsTemplateVO {
    @JsonProperty("id")
    private Integer temp_id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("field")
    private String scope;
}
