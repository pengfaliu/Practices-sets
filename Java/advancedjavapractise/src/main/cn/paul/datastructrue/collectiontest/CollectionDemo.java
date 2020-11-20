/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package main.cn.paul.datastructrue.collectiontest;

import java.util.*;

/**
 * Created by lfp on 2020/11/17.
 */
public class CollectionDemo {
    public void collectionD (){
        HashSet<Float> floats = new HashSet<>(100);
        floats.add(new Float(100));
        floats.add(new Float(1000));
        System.out.println(floats.size());

        floats.remove(new Float(100));
        System.out.println(floats.size());


        floats.removeAll(floats);
        System.out.println(floats.size());
    }

    public void  arrayListD() {
        ArrayList<String> arrayList = new ArrayList<>();
        System.out.println("arrayList length is : " + arrayList.size());

        arrayList.add(0, "liu");
        arrayList.add(1, "fa");
        arrayList.add(2, "peng");

        System.out.println("arrayList length is : " + arrayList.size());
        System.out.println(arrayList.iterator());

        for (String x : arrayList) {
            System.out.println(x+','+x.length());
        }
    }
    public void treeSetD () {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(100);
        treeSet.add(101);
        treeSet.add(102);
        treeSet.add(103);
        treeSet.ceiling(199);

        System.out.println("treeSet length is: " + treeSet.size());

        for (Integer x : treeSet) {
            System.out.println(x + "--" + x.doubleValue() + "--" + x.hashCode()
            + "--" + x.byteValue());
        }

        Iterator<Integer> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    public void mapTestD () {
        Map<Integer,String> map = new HashMap<Integer,String>();

        map.put(1,"beijing");
        map.put(2,"shanghai");
        map.put(3, "xiamen");

        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<Integer,String> entry : map.entrySet()){
            System.out.println("Key is:" + entry.getKey() + "-- Value is :" + entry.getValue());
        }

    }


    public void linkedListD (){
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.add("100");
        linkedList.add("1000");
        System.out.println(linkedList.get(0));
        System.out.println(linkedList);
    }
}
