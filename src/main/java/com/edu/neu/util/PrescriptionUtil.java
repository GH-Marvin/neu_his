package com.edu.neu.util;

public class PrescriptionUtil {
    public static String transformToName(String prescription_name){
        String[] split = prescription_name.split("：");
        return split[1];
    }

}
