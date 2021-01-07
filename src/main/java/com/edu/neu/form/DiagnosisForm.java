package com.edu.neu.form;

import com.edu.neu.vo.WestDiagnoseItemVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class DiagnosisForm {
    @JsonProperty("record_id")
    @NotEmpty(message = "病历号不能为空！")
    private String caseNumber;
    @JsonProperty("name")
    private String realName;
    @JsonProperty("sex")
    private String sex;
    @JsonProperty("age")
    private String age;
    @JsonProperty("regist_id")
    private Integer registId;
    @JsonProperty("complain")
    private String readme;
    @JsonProperty("ill_history")
    private String present;
    @JsonProperty("treat_condition")
    private String presentTreat;
    @JsonProperty("pre_history")
    private String history;
    @JsonProperty("allergy")
    private String allergy;
    @JsonProperty("health_checkup")
    private String physique;
    @JsonProperty("check_advice")
    private String proposal;
    @JsonProperty("attention")
    private String careful;
    private List<WestDiagnoseItemVO> data;
    private Integer caseState;
    private Integer diagnoseCate;
}
