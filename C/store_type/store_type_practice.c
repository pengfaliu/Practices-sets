//
//  store_type_practice.c
//  
//
//  Created by liufapeng on 2019/3/13.
//

#include "store_type_practice.h"

/* 函数声明 */
void func1(void);
void func2(void);

static int count = 10;



int main()
{
    while (count--) {
        func1();
        func2();
    }
    
    return 0;
}

void func1(void)
{
    /* 'thingy' 是 'func1' 的局部变量 - 只初始化一次
     * 每次调用函数 'func1' 'thingy' 值不会被重置。
     */
    static int thingy=5; //静态类变量，会常驻内存，直到第二次初始化，如果重复运行程序，不会二次初始化，需要重新编译后才能第二次初始化
    thingy++;
    printf("thingy is %d, count is %d\n",thingy,count);
}

void   func2(void)
{
    count--;
    printf("count decreased is: %d\n",count);
    
}
