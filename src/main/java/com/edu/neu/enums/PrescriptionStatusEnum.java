package com.edu.neu.enums;

import lombok.Getter;

@Getter
public enum PrescriptionStatusEnum {
    STAGING(168,"暂存"),
    SUBMITTED(169,"已开立"),
    CANCEL(170,"作废");


    private Integer code;
    private String msg;

    PrescriptionStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
