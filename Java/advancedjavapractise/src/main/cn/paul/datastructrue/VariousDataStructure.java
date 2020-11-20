package main.cn.paul.datastructrue.datastructure;

import main.cn.paul.datastructrue.collectiontest.CollectionDemo;
import main.cn.paul.datastructrue.dictionarytest.DictionaryDemo;
import main.cn.paul.datastructrue.hashtabletest.HashTableDemo;
import main.cn.paul.datastructrue.propertisetest.PropDemo;
import main.cn.paul.datastructrue.stacktest.StackDemo;

import java.util.*;

/**
 * Created by lfp on 2020/11/14.
 * @author  lfp
 */
public class VariousDataStructure {
    public static void main(String[] args) {

/*        // 打印出命令行的参数args数组
        System.out.println("parameters are: " + args.length);*/

/*        for (String s : args) {
            System.out.println(s);
        }*//*

        for (int i= 0 ; i< args.length; i++) {
            System.out.println("index ["+i+"] is "+ args[i] );
        }*/

        /*// 枚举类型数据结构测试
        EnumerationTester e = new EnumerationTester();
        e.enumtest();*/

/*        //BitSet 数据结构测试
        BitSetDemo a = new BitSetDemo();
        a.bitSetDemo();*/

 /*       //Vector 数据结构测试
        VectorDemo v = new VectorDemo();
        v.vectorD();*/

/*        // stack 数据结构测试
        Stack<Integer> st = new Stack<>();
        System.out.println("stack: " + st);


        StackDemo s = new StackDemo();
        s.stackpeek(st);
        System.out.println();
*//*
        try {

        }
        catch (EmptyStackException e){
            System.out.println("stack is empty!\n" + e);
        }
*//*
        //push 后
        s.showpush(st,10);
        s.showpush(st,100);
        s.showpush(st,100);
        s.stackpeek(st);

        //pop 后
        s.showpop(st);
        s.stackpeek(st);*/

/*        //map 数据结构测试
        DictionaryDemo a = new DictionaryDemo();
        a.dictionaryD();
        System.out.println(a.hashCode());*/

/*        // HashTable 数据结构测试
        HashTableDemo a = new HashTableDemo();
        a.hashTabled();*/
/*
        // Propertise 数据结构测试

        PropDemo b = new PropDemo();
        b.propD();*/

        //collection and map
        CollectionDemo  collectionDemo = new CollectionDemo();
        collectionDemo.collectionD();

        System.out.println("--start arrralist ");
        collectionDemo.arrayListD();

        System.out.println("--start treeSet ");
        collectionDemo.treeSetD();

        System.out.println("--start hashmap ");
        collectionDemo.mapTestD();

        System.out.println("--start linkedList");
        collectionDemo.linkedListD();

        int a = 9*8*19*39;
        System.out.println(a + ": " + (9+8+19+39)/4 + ":: " + Math.pow(a,4));
    }



}
