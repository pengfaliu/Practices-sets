/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package main.cn.paul.datastructrue.dictionarytest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lfp on 2020/11/15.
 */
public class DictionaryDemo {
    public void dictionaryD () {
        //由于dictionary 已经不再使用,所以下面的例子采用Map类型

        Map<String,Integer> m1 = new HashMap<>(100);
        m1.put("Zara", 8);
        m1.put("Mahnaz", 31);
        m1.put("Ayan", 12);
        m1.put("Daisy", 14);

        System.out.println();
        System.out.println(" Map Elements");
        System.out.print("\t" + m1);
       System.out.println("\nhashcode is :" + m1.hashCode());

    }
}


