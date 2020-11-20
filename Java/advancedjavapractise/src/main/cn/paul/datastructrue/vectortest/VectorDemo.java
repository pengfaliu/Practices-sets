package main.cn.paul.datastructrue.vectortest;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by lfp on 2020/11/15.
 */
public class VectorDemo {
    public void vectorD () {
        Vector  vector1 = new Vector(5);
        System.out.println("Initial vector size is: " + vector1.size());
        System.out.println("Initial vector capacity: " + vector1.capacity());

        vector1.add(0, new Float(10000));
        vector1.addElement(new Float(100));
        vector1.addElement(new Float(1));
        vector1.addElement(new Float(2));
        vector1.addElement(new Float(3));
        vector1.addElement(new Float(4));

        System.out.println("vector1 capacity : " + vector1.capacity());


        System.out.println(vector1.elementAt(2));
        System.out.println(vector1.firstElement());
        System.out.println(vector1.lastElement());


        vector1.addElement(new Integer(11));
        vector1.addElement(new Integer(12));
        vector1.addElement(new Float(9.4));
        vector1.addElement(new Integer(10));

        vector1.addElement(new Integer(10));
        vector1.addElement(new Integer(10));
        vector1.addElement(new Integer(10));
        vector1.addElement(new Integer(10));
        vector1.addElement(new Integer(10));
        vector1.addElement(new Integer(10));

        System.out.println("vector1 capacity : " + vector1.capacity());

        Enumeration vEnum = vector1.elements();
        System.out.println("\nElements in vector:");
        while (vEnum.hasMoreElements()){
            System.out.print(vEnum.nextElement() + " ");
        }
        System.out.println();

        System.out.println(vector1.contains(new Float(10000))) ;
    }


}
