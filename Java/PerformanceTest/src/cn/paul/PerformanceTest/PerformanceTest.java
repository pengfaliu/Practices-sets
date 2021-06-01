package cn.paul.PerformanceTest;

/**
 * Created by lfp on 2021/2/10.
 */

import java.lang.System;

public class PerformanceTest {
    public static void main(String[] args)
    {
        int MIN=1;
        int MAX=100000;
        int i,j;
        long  total = 0;
        for(i=MIN;i<=MAX;i++){
            for(j=MIN;j<=MAX;j++) {
                total += (i+j);
            }
        }
        System.out.println(total);
    }
}




