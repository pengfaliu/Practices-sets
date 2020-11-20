package cn.paul.QuoteAssign;

/**
 * Created by lfp on 2020/10/27.
 */
public class QuoteAssign {
    public  static  void  main(String[] args){
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();

        apple1.w = 10;
        apple2 = apple1;
        apple2.w = 20;
        System.out.println("apple1-> "+apple1.w+" "+"apple2-> "+apple2.w);
        System.out.print(apple1.w==100?1:2);

    }
}

class Apple {
    int w;
    public int weight(int num){
        return num;
    }
}