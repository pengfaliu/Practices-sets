//
//  array.c
//  
//
//  Created by liufapeng on 2019/3/16.
//

#include <stdio.h>

int   array[100];
int i ,j ;

int main ()
{
    for (i=0;i<100;i++)
    {
        array[i]=i+  100;
    }

    for(j=0;j<100;j++)
    {
        printf("Element[%d] = %d\n",j,array[j]);
    }
    return 0;
}
