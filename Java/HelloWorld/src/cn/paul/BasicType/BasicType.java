package cn.paul.BasicType;

import cn.paul.helloWorld.HelloWorld;
import cn.paul.limittype.PackageAndClassLimit;

/**
 * Created by lfp on 2020/10/25.
 *
 */
public class BasicType {
    /**
     *
     * @param args
     */
    public  static void main(String[] args) {
        /**
         *
         */
        test();

    }
    public static void test() {
        /*
        正向:byte>short>int>long>float>double
        反向:double > float > long >int > short > char > byte
         */
        byte type = (byte) 130;
        byte a = 8;
        int b = a;
        byte k = -8;
        int j = k;
        char i = 64;
        int l = (int) i;

        HelloWorld helloWorld = new HelloWorld();
        PackageAndClassLimit lt = new PackageAndClassLimit();

        System.out.printf("a is %s\n",Integer.toBinaryString(a));
        System.out.printf("b is %s\n", Integer.toBinaryString(b));
        System.out.printf("k is %s\n", Integer.toBinaryString(k));
        System.out.printf("j is %s\n", Integer.toBinaryString(j));
        System.out.println(type);
        System.out.printf("character i is %c\n", i);
        System.out.printf("l is %d\n", l);
        System.out.println(HelloWorld.add(10, 9));

        lt.publicclass();
        int xlt = 8;
        int xlu = 8;
        Integer xyl = xlt; //自动装箱
        Integer xxl = new Integer(xlt); //手动装箱

        Integer ops = 129;
        Integer opt = 129;


        Integer qx = new Integer(100);
        Integer qt = new Integer(100);

        System.out.println(xlt == xlu);
        System.out.println(ops == opt);
        System.out.println(qx == qt);
        System.out.println("a"+xlt+"xxx");

        int apple1 = 1;
        int apple2 = apple1;

        System.out.println("apple1-> "+apple1+" "+"apple2-> "+apple2);
        apple1 =2;
        System.out.println("apple1-> "+apple1+" "+"apple2-> "+apple2);
    }
}
