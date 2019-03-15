//
//  relational_operator.c
//  
//
//  Created by liufapeng on 2019/3/14.
//

#include <stdio.h>
int main ()
{
    int a = 21;
    int b = 10;
    int c ;
    //
    if (a == b)
    {
        printf("Line 1 -a equal b\n");
    }
    else
    {
        printf("Line 1 -a is not equal b\n");
    }
    
    //
    if (a < b)
    {
        printf("Line 2  -a less than b\n");
    }
    else
    {
        printf("Line 2 -a not less than b\n");
    }
    //
    if (a > b)
    {
        printf("Line 3 -a  more than b\n");
    }
    else
    {
        printf("Line 3 -a not more than b\n");
    }
    /* 改变 a 和 b 的值 */
    a = 5;
    b = 20;
    if ( a <= b )
    {
        printf("Line 4 - a 小于或等于 b\n" );
    }
    if ( b >= a )
    {
        printf("Line 5 - b 大于或等于 a\n" );
    }
}
