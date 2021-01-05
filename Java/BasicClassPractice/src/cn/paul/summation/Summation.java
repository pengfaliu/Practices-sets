package cn.paul.summation;

import java.util.Scanner;

/**
 * Created by lfp on 2020/11/24.
 */
public class Summation {

    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        summationFun(n);
        summationFun2(n);

    }

    public static void summationFun(int n) { //f(n) = 1+1/2+1/3+1/4+1/5..+1/n)
        double sum = 0.0;

        for (int i = 1; i < n; i++) {
            sum +=  1.0/i;
        }

        System.out.printf(" f(n) = 1+1/2+1/3+1/4+1/5..+1/n) summation are %.2f:\n", sum);
    }

    public static void summationFun2(int n) { //f(n) = 1-1/2+1/3-1/4+1/5..-1/n)
        double sum = 0.0;
        int sign = 1;

        for (int i = 1; i < n; i++) {
            sum +=  sign * 1.0/i;
            sign = -sign; //负负得正的思想
        }

        System.out.printf(" f(n) = 1-1/2+1/3-1/4+1/5..-1/n) summation are %.2f:\n", sum);
    }
}
