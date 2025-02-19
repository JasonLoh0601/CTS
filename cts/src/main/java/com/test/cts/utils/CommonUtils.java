package com.test.cts.utils;

public class CommonUtils {

    public static Double scaleTo8Decimal( Double v){
        if(v == null){
             return 0.0;
        }
        return Math.round(v * 100000000.0) / 100000000.0;
    }
}
