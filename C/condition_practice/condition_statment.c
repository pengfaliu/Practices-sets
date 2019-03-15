//
//  condition_statment.c
//  
//
//  Created by liufapeng on 2019/3/15.
//

#include "condition_statment.h"

int main(void)
{
    int num,num2;
    
    printf("输入2个数字 : ");
    scanf("%d,%d",&num,&num2);
    
    (num%2==0)?printf("num偶数"):printf("num奇数");
    printf("\n");
    (num2%2==0)?printf("num2偶数"):printf("num2奇数");
    printf("\n");
    return 0;
}

