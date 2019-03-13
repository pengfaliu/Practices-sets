//
//  union_show.c
//  
//
//  Created by liufapeng on 2019/3/13.
//

#include "union_show.h"
union Data
{    int i ;
    float f;
    char str[20];
};

int main()
{
    union Data data;
    printf("Memory size occupied by data : %d\n", sizeof(data));
    return 0;
    
};
