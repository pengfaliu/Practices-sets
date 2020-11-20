package cn.paul.NumAndMath;

/**
 * Created by lfp on 2020/10/31.
 */
public class NumAndMath {
    public static void main(String args[]){
        IntegerMethod();
        MathClass();

    }
    static void IntegerMethod(){
        Integer x = 5;
        x +=10;
        System.out.println(x);
    }
    static  void MathClass(){
        int a =  Math.abs(-10);
        double b =  Math.asin(1.0);
        double c =  Math.atan(2.0);
        double d =  Math.acos(3.0);
        double e =  Math.cos(1);
        double f=  Math.sin(2);
        System.out.printf("a=%d,b=%f,c=%f,d=%f,e=%f,f=%f \n",a,b,c,d,e,f);
    }
}
