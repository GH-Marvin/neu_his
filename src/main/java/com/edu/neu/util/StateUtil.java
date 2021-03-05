package com.edu.neu.util;

public class StateUtil {
    public static String getStateByCode(Integer code) {
        switch (code) {
            case 1:
                return "待缴费";
            case 2:
                return "已缴费";
            case 3:
                return "已发药";
            case 4:
                return "已消费";
            default:
                return null;
        }
    }
}
