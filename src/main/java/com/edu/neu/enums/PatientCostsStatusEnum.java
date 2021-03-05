package com.edu.neu.enums;

import lombok.Getter;

@Getter
public enum PatientCostsStatusEnum {
    WAIT_FOR_PAY(1 , "待缴费"),
    NO_SPENDING(2 , "未消费"),
    REGISTERED(3 , "已登记"),
    SENDING(4 , "已发放");


    private Integer code;
    private String msg;

    PatientCostsStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
