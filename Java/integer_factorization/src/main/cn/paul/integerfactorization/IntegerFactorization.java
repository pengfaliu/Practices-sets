package main.cn.paul.integerfactorization;

import java.util.Scanner;

/**
 * Created by lfp on 2020/11/18.
 * @author lfp
 *
 *
 * @version 1.0
 */
public class IntegerFactorization {
    public static void  main(String[] args) {
        System.out.print("请输入一个整数: ");
        Scanner scanner = new Scanner(System.in);
        integerFactorization(scanner.nextInt());
    }

    public static int integerFactorization (int integernum) {
        int SUCCESS=0;
        int FACTOR=10; //分解因子
        int count = 0; //计输入的长度数字
        int result = 0;

        do {
            int digital = integernum % FACTOR;
            result = result*FACTOR+digital;
            integernum = integernum / FACTOR;
            count++;

        } while (integernum>0); //exit

        System.out.println("您输出的是 "+ count + " 位数!") ;
        System.out.println("逆序数字为: " + result);

        return SUCCESS;
    }
}
