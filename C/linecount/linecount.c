#include <stdio.h>

/* 统计输入的行数*/

int main ()
{
    long c,n1;

    n1 = 0;
    while ( (c = getchar ( ) ) != EOF )
        if ( c == '\n' )
            ++n1;
    printf ("%ld\n",n1);
}
