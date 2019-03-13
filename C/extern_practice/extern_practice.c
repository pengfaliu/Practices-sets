//
//  extern_practice.c
//  
//
//  Created by liufapeng on 2019/3/13.
//

#include "extern_practice.h"
// 函数外定义变量 x 和 y
int x;
int y;
int add2num()
{
     // 函数内声明变量 x 和 y 为外部变量
    extern int x;
    extern int y;
    // 给外部变量（全局变量）x 和 y 赋值
    x = 1;
    y = 2;
    return x+y;
}

int main()
{
    int result;
     // 调用函数 addtwonum
    result = add2num();
    printf ("\t result is :%d \n",result);
    return 0 ;
}
