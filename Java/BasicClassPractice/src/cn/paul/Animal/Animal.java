package cn.paul.Animal;

/**
 * Created by lfp on 2020/11/11.
 */
class TestAbstractClass {
    public static void main(String[] args) {
        Animal abc = new dog();
        Animal bird = new birds();

        abc.type();
        abc.eyes();
        abc.legs();
        abc.feather();
        abc.test();

        System.out.println("---华丽的分割线-----\n");

        bird.type();
        bird.eyes();
        bird.legs();
        bird.feather();
        bird.test();
    }

}


public abstract class Animal {

    public abstract String type(); //类型

    public abstract int age(); //年龄

    public abstract int eyes(); //眼睛数量

    public abstract int legs(); //腿的数量

    public abstract int feather(); //羽毛类型

    public void test(){
        System.out.println("No-abstract method");
    }
}
 class  dog extends Animal {

    @Override
    public String type(){
        System.out.println("这是只狗");
        return "";

    }

    @Override
    public int age() {
        System.out.println("4 "+ "岁");
        return  0;
    }

    @Override
    public int eyes(){
        System.out.println("2 "+ "只眼睛");
        return 0;
    }

    @Override
    public  int legs(){
        System.out.println("4 " +"条腿");
        return 0;
    }

     @Override
    public int feather(){
        System.out.println("没有羽毛");
        return  0;
    }

}

class birds extends Animal {

    @Override
    public String type(){
        System.out.println("这是只鸟");
        return "";

    }

    @Override
    public int age() {
        System.out.println("1 "+ "岁");
        return  0;
    }

    @Override
    public int eyes(){
        System.out.println("2 "+ "只眼睛");
        return 0;
    }

    @Override
    public  int legs(){
        System.out.println("2 " +"条腿");
        return 0;
    }

    @Override
    public int feather(){
        System.out.println("有羽毛");
        return  0;
    }

    public void test(){
        System.out.println("一只小鸟的测试");
    }
}