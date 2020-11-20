package main.cn.paul.datastructrue.bitsettest;

import java.util.BitSet;

/**
 * Created by lfp on 2020/11/15.
 */
public class BitSetDemo {
    public void bitSetDemo(){
        int LENB = 32;
        int LENC = 16;

        BitSet b = new BitSet(LENB);
        BitSet c = new BitSet(LENC);

        for (int i=0;i<LENB; i++ ){
            if((i%2) == 0 ) b.set(i);
            if((i%5) == 0 ) c.set(i);
        }

        //设置标志位
        System.out.println("\nInitial pattern in bits1: " + b);
        System.out.println("Initial pattern in bits1: " + c);

        //两组标志位作 "与" 操作
        b.and(c);
        System.out.println("\nb AND c: " + b);

        //两组标志位作 "或" 操作
        b.or(c);
        System.out.print("\nb or c " + b);

        //两组标志位作 "异或" 操作
        b.xor(c);
        System.out.println("\nb xor c" + b);
    }


}
