/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package main.cn.paul.serializable;

import java.io.*;

/**
 * Created by lfp on 2020/11/22.
 */
public class SerializableDemo {

    public  static void main(String[] args) {
        Employee employee = new Employee();
        employee.name = "paul";
        employee.address = "chengdu";
        employee.number = 12345;
        employee.SSN = 13456789;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/tmp/employee.ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(employee);
            outputStream.close();
            fileOutputStream.close();
            System.out.println("Serialized data is saved in /tmp/employee.ser");
        }
        catch (IOException i){
            i.printStackTrace();
        }

        System.out.println("---- after do Deserializable ----\n ");
        Deserializable deserializable = new Deserializable();
        deserializable.deserializable();
    }


}

class Employee implements Serializable {

    public String name;
    public String address;
    public transient int SSN;
    public int number;
    public void mailCheck()
    {
        System.out.println("Mailing a check to " + name + " " + address);
    }
}

class Deserializable {
    Employee e = null;

    public void deserializable(){

        try {
            FileInputStream fileInputStream = new FileInputStream("/tmp/employee.ser");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            e = (Employee) inputStream.readObject(); //读出来的必须强制对象类型转换
            fileInputStream.close();
            inputStream.close();;

        }
        catch (IOException i) {
            i.printStackTrace();
        }
        catch (ClassNotFoundException k) {
            k.printStackTrace();
            System.out.println("Employee class not found");
        }

        System.out.println("Deserialized Employee...");
        System.out.println("Name: " + e.name);
        System.out.println("Address: " + e.address);
        System.out.println("SSN: " + e.SSN);
        System.out.println("Number: " + e.number);


    }
}