package cn.paul.datetype;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lfp on 2020/11/8.
 */

public class DateTest {
    public static void main(String[] args) throws InterruptedException {
        Date d = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(d.getTime());
        System.out.println(d.toString());
        System.out.println(d.after(new Date(99, 12, 20)));
        System.out.println(d.hashCode());

        Thread.sleep(1000 * 3);

        Date x = new Date();
        System.out.println(ft.format(x));

        StaticCodeBlock a = new StaticCodeBlock();
        System.out.println(a.test);
        int value = a.staticblocktest();
        System.out.println(value);

        Codeblock k = new Codeblock();

        Person p = new Person();

        System.out.println("------------");

        Student z = new  Student();
        z.show();


    }
}

class  StaticCodeBlock {
    int test = 10;
    int i ;
    static {
        System.out.println("this is a static code block.");
    }
    int staticblocktest (){
        System.out.println("test test test," + i);
        return test;
    }
}

class  Codeblock {
    // 动态代码块  执行顺序2
    {
        System.out.println("dynamic code block in initialization;");
    }

    // 静态代码块 执行顺序1
    static {
        System.out.println("static code block ");
    }

    // 构建函数 执行顺序3
    public Codeblock() {
        System.out.println("call 调用函数");

    }
}


class Person {

    int height;
    int age;
    String bloodType;
    String name ;
    float weight;
    int cry; // 1代表会快,0代表不会哭

    public  Person (){

    }

    public  Person(String name1) {
        name = name1 ;

    }

    public  Person (String name2, int age1 ){
        name = name2;
        age = age1;

    }
    public String getName(String n){
        return name;
    }

    public int getHeight(int h) {
        return height;
    }
}

class Student extends Person {
    String  profession = "student";
    int score;
    public Student(){
        score=0;
    }

    public void show (){
        System.out.println("姓名："+getName("liufapeng")+"\n身高："+getHeight(64)+"\n分数："+score);
    }


}