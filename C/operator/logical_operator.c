//
//  logical_operator.c
//  
//
//  Created by liufapeng on 2019/3/14.
//

#include <stdio.h>

int main()
{
    int a = 5;
    int b = 20;
    int c;
    
    if ( a && b)
    {
        printf("Line 1  - condition is true\n");
    }
    if (a||b)
    {
        printf("Line 2 - condition is true\n");
    }
    /* 改变 a 和 b 的值 */
    a = 0;
    b = 10;
    if ( a && b )
    {
        printf("Line 3 - 条件为真\n" );
    }
    else
    {
        printf("Line 3 - 条件不为真\n" );
    }
    if ( !(a && b) )
    {
        printf("Line 4 - 条件为真\n" );
    }
    
}
