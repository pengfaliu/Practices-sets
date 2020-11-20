package cn.paul.scannertest;

import java.util.Scanner;

/**
 * Created by lfp on 2020/11/18.
 */
public class ScannerTest {
    public static void main(String[] args) {

/*
        for (String x: args) { // 命令行参数
            System.out.println(x);
        }*/
        //getAvg();
        guessNumber();
    }

    public static void scannertest(){

        Scanner in = new Scanner(System.in); //系统终端输入
        System.out.println(in.nextInt()) ;
        System.out.println(in.next());
        System.out.println(in.nextInt());
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

    public static void guessNumber(){
        int result;
        int guess_num;
        int count = 0;

        //Random x = new Random(100);
        result = (int) (Math.random()*100+1); //生成100以内的整数
        Scanner num = new Scanner(System.in);
        System.out.println("数字我想好了,请输入我想的哪个数字吧:");

        do {

            guess_num = num.nextInt();
            count+=1;

            if (guess_num < result) {
                System.out.println("你的数小了哦!");
            }

            if (guess_num > result) {
                System.out.println("你的数大了哦!");
            }

        } while (guess_num != result);

        System.out.println("恭喜你猜对了,共猜了" + count +"次");
    }
}

