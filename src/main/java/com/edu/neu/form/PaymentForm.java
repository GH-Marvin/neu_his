package com.edu.neu.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentForm {
    @JsonProperty("bill_id")
    private String invoiceNum;
    @JsonProperty("record_id")
    private String caseNumber;
    @JsonProperty("name")
    private String realName;
    @JsonProperty("payType")
    private Integer feeType;
    @JsonProperty("should_pay")
    private Double money;
    @JsonProperty("fact_pay")
    private Double factMoney;
    @JsonProperty("back_pay")
    private Double backMoney;
    @JsonProperty("selected_data")
    private String patientCostsData;
    @JsonProperty("user_id")
    private Integer userId;
}
