package com.edu.neu.util;

import java.util.Date;

public class DateUtil {
    public static String transformDateToDay(Date date){
        Date today = new Date();
        if(date.equals(today)){
            return "今天";
        }else if(date.after(today)){
            return "预约";
        }else {
            return "历史";
        }
    }
}
