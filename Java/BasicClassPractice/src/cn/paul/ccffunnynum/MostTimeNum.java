package cn.paul.ccffunnynum;

import java.util.Scanner;

/**
 * Created by lfp on 2020/11/20.
 * CCF 的试题 真不知道是干啥的,哈哈
 */
public class MostTimeNum {
    public static void main(String[] args) {
        findNum();
    }

    public static void findNum(){
        Scanner inputnumbers = new Scanner(System.in);

        int num = inputnumbers.nextInt();
        int[] num_array = new int[10001];

        for (int i = 0; i< num; i++) {
            ++num_array[inputnumbers.nextInt()];
            System.out.println(num_array[inputnumbers.nextInt()]);
        }


        int maxCount = -1 ;
        int result = 0;

        for (int i=1 ; i<=10000;i++ ){
            if (num_array[i] > maxCount){
                maxCount = num_array[i];
                result = i ;
            }
        }

        System.out.println(result);
    }
}
