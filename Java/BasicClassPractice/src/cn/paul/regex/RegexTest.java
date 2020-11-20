package cn.paul.regex;

import java.util.regex.Pattern;

/**
 * Created by lfp on 2020/11/9.
 */

public class RegexTest {
    public static void  main(String[] args) {
        String content = " you are best student, but you should work hard more";
        final String patten = ". you .";


        boolean isMatch = Pattern.matches(content,patten);
        System.out.println(isMatch);
    }

    public final int test(int a, int b) {
        return (a+b);
    }

}

class test extends RegexTest {

    public int testx(int a, int b) {

        final  String URL ;
        int k=0;
        k = a;

        return (k+a+b);


    }
}