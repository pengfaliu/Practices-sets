package cn.pual.InPalceSwap;


import java.lang.System;


/*
@author : liufapeng
@return

这个是a,b的两个变更交换程序
 */
public class InPlaceSwap {
    public static void main(String[] args) {

        int a = 100;
        int b = 200;

        System.out.println("----before---");
        System.out.printf("a is %d\n", a);
        System.out.printf("b is %d\n",b);


        b = a ^ b;
        a = a ^ b;
        b = a ^ b;

        System.out.println("----after replace---");
        System.out.printf("a is %d\n", a);
        System.out.printf("b is %d\n", b);
    }
}