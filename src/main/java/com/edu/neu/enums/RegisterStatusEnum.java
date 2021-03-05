package com.edu.neu.enums;

import lombok.Getter;

@Getter
public enum RegisterStatusEnum {

    REGISTER(1,"挂号"),
    DIAGNOSIS(2,"诊断"),
    PAY(3,"缴费"),
    PRESCRIBE(4,"开药"),
    BACK_REGISTER(-1,"退号"),
    BACK_PAY(-3,"退费"),
    BACK_PRESCRIBE(-4,"退药");

    private Integer code;
    private String msg;

    RegisterStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
