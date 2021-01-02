package com.edu.neu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WestDiagnosisUtil {
    public static Map<Integer,List> transformToMap(String data){
        System.out.println(data);
        String[] split_data = data.split(";");
        if(split_data.length>0){
            Map<Integer,List> map = new HashMap<>();
            List<Integer> idList = new ArrayList<>();
            List<String> timeList = new ArrayList<>();
            for(int j = 0 ; j<split_data.length;j++) {
                String[] split = split_data[j].split("-");
                if(split.length>1){
                    for(int i=1;i<split.length;i++){
                        idList.add(Integer.valueOf(split[i]));
                        timeList.add(split[0]);
                        System.out.println("id:"+split[i]);
                        System.out.println("time:"+split[0]);
                    }
                }else {
                    return null;
                }

            }
            map.put(0,idList);
            map.put(1,timeList);
            return map;
        }else {
            return null;
        }

    }
}
