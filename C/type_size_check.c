// author pengfaliu@163.com
//  type_size_check.c
//  
//
//  Created by liufapeng on 2019/11/23.
//

#include <stdio.h>
#include <limits.h>

int main(void) {
    printf ("int size is %lu \n",sizeof(int));
    printf ("char size is %lu \n",sizeof(char));
    printf ("long size is %lu \n",sizeof(long));
    printf ("short size is %lu \n",sizeof(short));
    printf ("unsigned char size is %lu \n",sizeof(unsigned char));
    printf ("unsigned short size is %lu \n",sizeof(unsigned short));
    printf ("unsigned int size is %lu \n",sizeof(unsigned int));
    printf ("unsigned long size is %lu \n",sizeof(unsigned long));
    printf ("long int size is %lu \n",sizeof(long int));
    printf ("long long size is %lu \n",sizeof(long long));
    printf ("double size is %lu \n",sizeof(double));
    printf ("long double size is %lu \n",sizeof(long double));
    printf ("float size is %lu \n",sizeof(float));
    
    return 0;
}

