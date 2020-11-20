package main.cn.paul.guessnum;

import java.util.InputMismatchException;
import java.util.Scanner;



/**
 * Created by lfp on 2020/11/18.
 * @author lfp
 * @version 1.0
 *
 */
public class GuessNumber {
    public static void main(String[] args){
        guessNumber();
    }

    public static void guessNumber(){
        int result;
        int guess_num = 0;
        int count = 0; //正常的猜测计数。
        int TIMES=10; // 正常猜测次数大于10次,提示多少次了。
        int illegal_count = 0; //非法猜测计数
        int illegal_TIMES = 10; //非法猜测大于10次时,提示,并退出游戏

        boolean SWITCH = true;  //正常与否的开关

        //Random x = new Random(100);
        result = (int) (Math.random()*1000+1); //生成100以内的整数


        System.out.println(" *------------------------------------*");
        System.out.println(" |我们来玩个游戏吧,猜一猜1-1000的整数 |\n |     我说一个数字,你来猜怎么样?     |");
        System.out.println(" *------------------------------------*");
        System.out.println("        .........          \n\n" + " 我想好了,请输入那个数字吧:");

            do {
                System.out.print(" ");
                Scanner num = new Scanner(System.in);

                try {

                    guess_num = num.nextInt();

                    count++;

                    if (count > TIMES) {
                        System.out.print(" 加油吧少年!都" + count + "次了!");
                    }

                    if (guess_num < result) {
                        System.out.println(" 你的数小了哦!@_@");
                    } else if (guess_num > result) {
                        System.out.println(" 你的数大了哦!&_&");
                    }else {
                        System.out.println("  \n ^_^ 哎哟! 恭喜你猜对了,正式的猜了" + count +"次!");
                        System.out.println("      你已经调皮了 " + illegal_count +" 次! ");
                        count = count+illegal_count; //输出所有次数的总和
                        System.out.println("      一共猜了" + count + "次!");

                        SWITCH = false; //如果猜对了结束游戏
                    }
                }catch (InputMismatchException e){
                    System.out.println(" 不要调皮,请输入一个整数!");
                    illegal_count++;
                    if (illegal_count > illegal_TIMES) {
                        System.out.println("  \n小样,你已经调皮" + illegal_count + "次了! 本次游戏结束,拜拜!\n");
                        break;
                    }
                }

            }while (SWITCH);

    }

    public static boolean isNumeric(String str){ //ascii 码值判断字符串是否为数字
        for(int i=str.length();--i>=0;){
            int chr=str.charAt(i);
            if(chr<48 || chr>57)
                return false;
        }
        return true;
    }

}
