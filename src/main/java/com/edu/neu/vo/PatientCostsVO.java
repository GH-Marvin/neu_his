package com.edu.neu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientCostsVO {
    @JsonProperty("pc_id")
    private Integer pcId;

    @JsonProperty("record_id")
    private String caseNumber;

    @JsonProperty("name")
    private String realName;

    @JsonProperty("project_name")
    private String name;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("num")
    private Double amount;

    @JsonProperty("dig_time")
    private String prescriptionTime;

    @JsonProperty("prescription_state")
    private String prescriptionState;

    @JsonProperty("invoice_id")
    private Integer invoiceId;

    @JsonProperty("pay_time")
    private String payTime;

    @JsonProperty("register_id")
    private Integer registerId;

    @JsonProperty("fee_type")
    private Integer feeType;

    @JsonProperty("state")
    private String state;
}
