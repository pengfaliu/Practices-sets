//
//  ternary_operator.c
//  
//
//  Created by liufapeng on 2019/3/15.
//

#include "ternary_operator.h"

int A = 10;
int B = 20;

char buy;

int sum,number;

int main()
{
    printf("Goods and price in this store as below \n A 商品每个十元；\n B 商品每个二十元；\n\n");
    printf("please input goods what you want(A 或 B):");
    scanf("%c",&buy);
    printf ("please input amount:");
    scanf("%d",&number);
    sum = buy =='A'?A*number:B*number;
    printf("\n you need %d %c goods,total %d.\n\n",number,buy,sum);
    return 0;
}
