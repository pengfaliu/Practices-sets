//
//  cycle_for.c
//  
//
//  Created by liufapeng on 2019/3/15.
//

#include <stdio.h>
int main()
{
    float i;
    for (i=0;;i++) // 中间如果没有条件，则永远的为真。
    {
        printf( "%9.10f :hello,world\n",i);// 9是占位数，.10 小数点后10位，
    }
}
