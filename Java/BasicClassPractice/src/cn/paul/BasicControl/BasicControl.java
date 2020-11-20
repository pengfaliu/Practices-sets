package cn.paul.BasicControl;

import java.util.Scanner;

/**
 * Created by lfp on 2020/11/1.
 */
public class BasicControl {

    public static void  main(String[] args){
        //todo
        //IfControl();
        //DoWhileControl();
        //WhileControl();
        ForControl();
    }
    static void IfControl(){
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        if (a != 1 && a ==2) {
            System.out.println("a=2");
        }
        else if (a == 1) {
            System.out.println("a is 1");
        }
        else {
            System.out.println("nothings");
        }

    }
    static  void DoWhileControl(){
        Scanner scanner = new Scanner(System.in);
        //整数分解
        int number ;
        int multiple = 10;
        int result = 0;

        System.out.print("请输入一个数字:");
        number = scanner.nextInt();
        do {
            int digital = number%multiple;
            System.out.print(digital); //求出个位数字
            result = result*10 + digital;
            number = number/multiple; //还剩下的整数
        } while (number >0);
        System.out.println("\n"+result);

    }

    static void WhileControl(){
        /*
        求出2 - 所输出数字的区间中所有素数,这个程序有问题。
         */
        //todo
        int start_number = 2;
        Scanner end_number  = new Scanner(System.in);

        int step=2; //执行步数
        System.out.println("请输入2- ?: ");

        int num = end_number.nextInt();
        int i = start_number; //找素数的除数

        outer:
        while (step <= num ) {
            while (i<step) {
                if (step % i == 0) {
                    System.out.print(step + "is not a prime");
                    continue outer;
                }
                i++;
            }
            step++;
            System.out.println(step + "is a prime");
        }
    }

    static void ForControl(){
        /*
        在给定的一个区间里,找出这个区间里的所有素数
         */
        Scanner end_number  = new Scanner(System.in);
        int num;
        int i;
        int end = end_number.nextInt();


        int primeNums = (end & 1) == 1 ? (end >>> 1) + 1 : end >>> 1; //数组个数

        int[] primeArray = new int[primeNums];
        int primeCount = 0; //数组元素个数


        if(end <= 1){ //素数不能为一个负数
            throw new IllegalArgumentException("N must be a non negative integer.");
        }

        if(end == 2) {
            System.out.println("2 is a minimum prime");
        }

        outer: //label

        for (num=3;num<=end;num++) {
            for (i = 2; i < num; i++) {
                if (num % i == 0) {
                    //System.out.println(num + " is not a prime.");
                    continue outer; //如果是合数,则直接跳到最外层重新开始
                }
            }
            System.out.println(num + " is a prime");
            primeArray[primeCount++]=num;

        }
        System.out.println("found prime Total " + primeCount);
    }
}
