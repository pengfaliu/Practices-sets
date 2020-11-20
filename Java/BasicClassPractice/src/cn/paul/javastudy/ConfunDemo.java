package cn.paul.javastudy;

/**
 * Created by lfp on 2020/11/9.
 */

public class ConfunDemo {
    public static void main(String[] args) {
        Pupil z=new Pupil("王二麻子",100,200);
        z.show();

        Pupil w=new Pupil();
        w.show();

        Pupil t = new Pupil("李清",165,50);
        t.show();
    }
}
class Student{                //父类Student
    public String name;
    public int height;
    public Student()
    {
        this.name="";
        this.height=0;
    }
    public Student(String n,int m)
    {
        name=n;
        height=m; //kg
    }
}
class Pupil extends Student{    //子类Pupil
    private int score;
    public Pupil(){
        super("未填写",0);    //使用super调用父类Student(String n,int m)方法，同时传递实际数值。super必须写在方法的首行。如果这里写super()，则调用的是父类中的Student()方法。
        score=0;
    }
    public Pupil(String x,int y,int z){        //
        super(x,y);              //使用super调用父类Student(String n,int m)方法,其中super中的参数名称必须与构造函数中的参数名称一致。
        score=z;
    }
    public void show(){
        System.out.println("姓名：" + name + "\n身高：" + height + "\n分数：" + score + "\n");

    }
}
