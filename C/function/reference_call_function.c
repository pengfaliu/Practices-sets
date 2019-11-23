//
//  reference_call_function.c
//  
//
//  Created by liufapeng on 2019/3/15.
//

#include <stdio.h>

void swap(int *x, int *y);

int main()
{
    int a = 100;
    int b = 200;
    
    printf("交换前，a 的值： %d\n", a );
    printf("交换前，b 的值： %d\n", b );
    
    /* 调用函数来交换值
     * &a 表示指向 a 的指针，即变量 a 的地址
     * &b 表示指向 b 的指针，即变量 b 的地址
     */
    swap(&a,&b);
    
    printf("交换后，a 的值： %d\n", a );
    printf("交换后，b 的值： %d\n", b );
    
    printf("before:&a is %p\n",&a);
    printf("before:&b is %p\n",&b);
    
    printf("after:&a is %p\n",&a);
    printf("after:&b is %p\n",&b);
    
    return 0;
}

void swap(int *x, int *y)
{
    int temp;
    temp = *x;
    *x = *y;
    *y = temp;
    
    return;
    
}
