import java.lang.System;

public class inplaceswap {
    public static void main(String[] args) {

        int a = 100;
        int b = 200;

        System.out.println("----before---");
        System.out.println("a is %d",a);
        System.out.println("b is %d",a);

        b = a ^ b;
        a = a ^ b;
        b = a ^ b;

        System.out.println("----after replace---");
        System.out.println("a is %d",a);
        System.out.println("b is %d",a);
    }
}