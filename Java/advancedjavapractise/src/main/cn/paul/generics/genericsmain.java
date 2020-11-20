package main.cn.paul.generics;

/**
 * Created by lfp on 2020/11/15.
 */
public class genericsmain {
    public static void main(String[] args) {

        Boxes<Integer> integerBox = new Boxes<>();
        Boxes<String> stringBox = new Boxes<>();
        Boxes<Float> floatbox = new Boxes<>();

        integerBox.add(10);
        stringBox.add("菜鸟教程");
        floatbox.add(new Float(10));

        System.out.printf("整型值为 :%d\n\n", integerBox.get());
        System.out.printf("字符串为 :%s\n", stringBox.get());
        System.out.printf("浮点型为 :%f\n\n",floatbox.get());


        Integer[] intArray = { 1, 2, 3, 4, 5 };
        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };


        System.out.println( "整型数组元素为:" );
        Boxes.printArray(intArray);

        System.out.println("\n双精度型数组元素为:");
        Boxes.printArray(doubleArray);

        System.out.println("\n字符型数组元素为:");
        Boxes.printArray(charArray);


        System.out.printf("%d, %d 和 %d 中最大的数为 %d\n\n",
                intArray[2], intArray[3], intArray[4], Boxes.maximum(intArray[2],intArray[3],intArray[4]));

        System.out.printf("%.1f, %.1f 和 %.1f 中最大的数为 %.1f\n\n",
                6.6, 8.8, 7.7, Boxes.maximum(6.6, 8.8, 7.7));

        System.out.printf("%s, %s 和 %s 中最大的数为 %s\n", "pear",
                "apple", "orange", Boxes.maximum("pear", "apple", "orange"));
    }
}
