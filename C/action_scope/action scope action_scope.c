//
//  action scope action_scope.c
//  
//
//  Created by liufapeng on 2019/3/16.
//
// 局部变量示例
#include <stdio.h>

int main ()
{
    /* 局部变量声明 */
    int a, b;
    int c;
    
    /* 实际初始化 */
    a = 10;
    b = 20;
    c = a + b;
    
    printf ("value of a = %d, b = %d and c = %d\n", a, b, c);
    
    return 0;
}
