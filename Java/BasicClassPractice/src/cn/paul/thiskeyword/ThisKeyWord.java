package cn.paul.thiskeyword;

/**
 * Created by lfp on 2020/11/11.
 * @author  liufapeng
 * @apiNote no api
 * @version v1.0
 */
public class ThisKeyWord {
    public static void main(String[] args) {
        System.out.println("this keyword testing...");

        Person person = new Person("刘发鹏",34,"13788888888");
        System.out.println(person.name);
        System.out.println(person.age);
        System.out.println(person.telNumber);

        Person person1 = new Person("小李子",120);
        System.out.println(person1.name);
        System.out.println(person1.age);
        System.out.println(person1.telNumber);

    }
}


class Person {
    String name = "未填写"; // 名字默认值
    int age = 0; //年龄默认值
    String telNumber = "000000000000"; //电话号码默认值

    public  Person(String name1, int age1){
        this.name = name1;
        this.age = age1;
    }

    public Person(String name2, int age2,String telNumber){
        this(name2,age2);
        this.telNumber = telNumber;
        this.testKey();
    }

    public void testKey (){
        System.out.println("测试this方法");
    }
}