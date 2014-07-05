#include <stdio.h>
#include <stdlib.h>

/* 统计输入的行数*/

int main ()
{
    long c,n1,n2,n3;

    n1 = 0;
    n2 = 0;
    n3 = 0;
    while ( (c = getchar ( ) ) != EOF )
        if ( c == ' ' )
        {
            ++n1;
            printf ("space count:%ld\n",n1);
        }
        else if ( c =='\t' )
        {
            ++n2;
            printf ("tab count:%ld\n",n2);
        }
        else if ( c == '\n' )
        {
            ++n3;
            printf ("enter count:%ld\n",n3);
        }
        else
        {
            exit (0);
        }
}
