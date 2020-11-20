package staticfield;

/**
 * Created by lfp on 2020/11/4.
 */
public class KeyWords {

    //name
    String name;
    //age
    int age;
    //username
    String userName;

    // password
    String userPassword;

    //constant
    public static  final int  MAX_LOGIN_TIMES = 10;


    public static void  main(String[] args){
        /* KeyWords kw = new KeyWords();
        kw.isLogin();
        */
        Apple a = new Apple();
        Apple b = new Apple();
        Apple c = new Apple();

        System.out.printf("%d,%d,%d\n",a.number,b.number,c.number);
        System.out.println(Apple.count);
    }

    public boolean isLogin(){
        int TIMES = 0;
        return  false;
    }
}

class  Apple{
    int number;
    static int count = 3;
    public Apple(){
        this.number = count--;
    }

}
