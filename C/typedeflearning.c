#include <stdio.h>
#include <stdlib.h>



typedef unsigned char BYTE;

BYTE b1,b2;

int main(void)  {
    b1 = 'x';
    b2 = 'y';
    printf("%c,%c is done\n",b1,b2);
    printf("size(b1) is %lu\n, size(b2) is %lu\n",sizeof(b1),sizeof(b2));
    return EXIT_SUCCESS;
}