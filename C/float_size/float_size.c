//
//  float_size.c
//  
//
//  Created by liufapeng on 2019/3/13.
//

#include "float_size.h"
int main()
{
    printf ("float store max byte is :%lu \n", sizeof(float));
    printf("float 最小值: %E\n", FLT_MIN );
    printf("float 最大值: %E\n", FLT_MAX );
    printf("精度值: %d\n", FLT_DIG );
    return 0;
    
}
