package main.cn.paul.generics;

/**
 * Created by lfp on 2020/11/15.
 */
public class Boxes<L> {

    private L t;

    public void add(L t) {
        this.t = t;
    }

    public L get() {
        return t;
    }

    public static < L > void printArray( L[] inputArray )
    {
        // 输出数组元素
        for ( L element : inputArray ){
            System.out.printf( "%s ", element );
        }
        System.out.println();
    }

    public static <K extends Comparable<K>> K maximum(K a,K b,K c)
    {
        K max = a; // 假设x是初始最大值
        if ( b.compareTo(max) > 0 ){
            max = b; //y 更大
        }
        if ( c.compareTo(max) > 0 ){
            max = c; // 现在 z 更大
        }
        return max; // 返回最大对象
    }
}