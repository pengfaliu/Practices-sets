//
//  type_transfer.c
//  
//
//  Created by liufapeng on 2019/3/13.
//

#include "type_transfer.h"
#include<stdio.h>

int main()
{
    float f,x=3.6,y=5.2;
    int i=4,a,b;
    a=x+y;
    b=(int)(x+y);
    f=10.0/i;
    printf("a=%d,b=%d,f=%f,x=%f\n",a,b,f,x);
    return 0;
}
