package cn.paul.interfacetest;

/**
 * Created by lfp on 2020/11/14.
 */
public class MammalInt implements Animal {

    public void eat() {
        System.out.println(" 需要每天吃东西" );
    }

    public void travel () {
        System.out.println("吃完东西就去旅行");
    }

    public int no0fLegs(){
        return  0;
    }

    public static void main(String args[]){
        MammalInt m = new MammalInt();
        m.eat();
        m.travel();
    }
}
