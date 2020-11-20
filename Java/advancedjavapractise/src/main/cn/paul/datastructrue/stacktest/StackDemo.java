package main.cn.paul.datastructrue.stacktest;

import java.util.Stack;

/**
 * Created by lfp on 2020/11/15.
 */
public class StackDemo {

    public void stackpeek (Stack<Integer> st){

        if (st.empty()) {
            System.out.println("The stack is empty~");
        }
        else {
            System.out.println("栈顶元素为: " + st.peek() + "\n");
        }

    }

    public void showpush(Stack<Integer> st, int a) {
        st.push(new Integer(a));
        System.out.println("push(" + a + ")");
        System.out.println("stack: " + st + "\n");
    }

    public void showpop(Stack<Integer> st) {
        System.out.print("pop -> ");
        Integer a = (Integer) st.pop();
        System.out.println(a);
        System.out.println("stack: " + st + "\n");
    }
}
