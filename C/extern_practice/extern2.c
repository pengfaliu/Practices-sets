//
//  extern2.c
//  
//
//  Created by liufapeng on 2019/3/13.
//

#include <stdio.h>
extern int count;

void write_extern(void)
{
    printf("count is %d\n",count);
}
