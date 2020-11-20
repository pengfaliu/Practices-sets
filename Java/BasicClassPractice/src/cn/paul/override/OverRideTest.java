package cn.paul.override;

import java.io.IOException;

/**
 * Created by lfp on 2020/11/11.
 */
public class OverRideTest {
    public  static void main(String[] args) {
        Animal dog1 = new dog("peter");
        dog1.move();
        System.out.println(dog1.jump(10));
        System.out.println(dog1.jump(10,"park"));

    }
}

class Animal {

    Animal() {

    }

    public void move(){
        System.out.println("动物可以移动");
    }

    public int jump(int times){
        return  times;
    }

    public  int jump(int times,String env) {
        return times;
    }

}


class dog extends  Animal {

   dog (String b) {
        //super();
        System.out.println("构造方法 "+b);
    }

    @Override
    public void move(){
        super.move();
        System.out.println("狗是可以跑的");
    }

    public void test (int a) throws IOException {
        System.out.println("It's a test ");
    }

    @Override
    public  int jump(int times) {
        return times;
    }

    @Override
    public  int jump(int times,String env) {
        System.out.println( env+" has jumped " + Integer.toString(times));
        return 0;
    }

}


class testexcepiton extends Exception {

}

interface test {
    public int x = 1;
    public int c = 1;

}