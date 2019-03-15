//
//  sundry_operator.c
//  
//
//  Created by liufapeng on 2019/3/14.
//

#include <stdio.h>


int main()
{
    int a = 4;
    short b ;
    double c ;
    int *ptr;
    
    /* sizeof the case of operator */
    printf("Line 1 - 变量 a 的大小 = %lu\n",sizeof(a));
    printf("Line 2 - 变量 b 的大小 = %lu\n",sizeof(b));
    printf("Line 3 - 变量 c 的大小 = %lu\n",sizeof(c));
    
    
    /* & 和 * 运算符实例 */
    ptr = &a; /* 'ptr'  现在包含 'a' 的地址 */
    printf("a 的值是 %d \n", a); //a=4
    printf("*ptr 是 %d \n", *ptr); //ptr =4
    printf("ptr is %lu \n",ptr);
    
    /* 三元运算符实例 */
    a = 10 ;
    b = ( a== 1) ? 20 : 30;
    printf ("b 的值是 %d\n",b); //b=30
    
    b = (a==10) ? 20 : 30 ;
    printf ("b 的值是 %d\n",b); //b=20
}
