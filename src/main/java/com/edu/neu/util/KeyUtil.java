package com.edu.neu.util;

import java.util.Random;

public class KeyUtil {
    //线程安全
    public static synchronized String createUniqueKey() {
        Random random = new Random();
        Integer key = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(key);
    }
    public static void main(String[] args) {
        System.out.println(createUniqueKey());
    }
}
