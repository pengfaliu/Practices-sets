//
//  extern1.c
//  
//
//  Created by liufapeng on 2019/3/13.
//

#include <stdio.h>
int count;
extern void write_extern(void);

int main()
{
    count =5;
    write_extern();
}
