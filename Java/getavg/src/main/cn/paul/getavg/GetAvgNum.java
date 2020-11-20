package main.cn.paul.getavg;

import java.util.Scanner;

/**
 * Created by lfp on 2020/11/18.
 */
public class GetAvgNum {

    public static void   main(String[] args) {
        int NORMAL_CODE = 0;

        getAvg();
    }


    private static void getAvg() { //平均数算法
        double w =0 ;
        double count=0; //读取的个数
        double avg;

        System.out.print("请输数:");
        Scanner in = new Scanner(System.in);


        while (in.hasNextDouble()) { //遇到eof停止
            w += in.nextDouble();
            count += 1;
        }

        if (count >0) {
            avg = w/count;
            System.out.println("和为:" + w);
            System.out.println("平均数为:" + avg);
        }
        else{
            System.out.println("没有整数输入");
        }
    }
}
