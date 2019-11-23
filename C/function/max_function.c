//
//  max_function.c
//  
//
//  Created by liufapeng on 2019/3/15.
//

#include "max_function.h"
// 函数声明
int max( int mum1,int num2);

int main(void)
{
    max(3,5);
    return 0;
}

//声明的函数定义
int max(int num1,int num2)
{
    //可以使用if ... else 实现
    num1>num2?printf("%d>%d\n",num1,num2):printf("%d<%d\n",num1,num2);
    return 0;
}


