//
//  operator1.c
//  
//
//  Created by liufapeng on 2019/3/14.
//

#include <stdio.h>
int main(void)
{
    int c ;
    int a = 10;
    c = a++ ; // c=10 ,a=11
    printf("先赋值后运算：\n");
    printf("Line 1 - c 的值是 %d\n", c );
    printf("Line 2 - a 的值是 %d\n", a );
    a = 10 ;
    c = a--; // c=10 a=9
    printf("Line 3 - c 的值是 %d\n", c );
    printf("Line 4 - a 的值是 %d\n", a );
    
    printf("先运算后赋值：\n");
    a = 10;
    c = ++a ;// c=11, a=11
    printf("Line 5 - c 的值是 %d\n", c );
    printf("Line 6 - a 的值是 %d\n", a );
    a = 10;
    c = --a; // c = 9, a=9
    printf("Line 7 - c 的值是 %d\n", c );
    printf("Line 8 - a 的值是 %d\n", a );
}
