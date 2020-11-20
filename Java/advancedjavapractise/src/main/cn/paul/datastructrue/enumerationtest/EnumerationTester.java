package main.cn.paul.datastructrue.enumerationtest;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by lfp on 2020/11/14.
 */
public class EnumerationTester {

    public void enumtest() {
        Enumeration<String> days;
        Vector<String> dayNames = new Vector<String>();
        dayNames.add("Monday");
        dayNames.add("Tuesday");
        dayNames.add("wednesday");
        dayNames.add("Thursday");
        dayNames.add("Friday");
        dayNames.add("Saturday");
        dayNames.add("sunday");
        days = dayNames.elements();

        while (days.hasMoreElements()){
            System.out.println(days.nextElement());
        }

    }
}

