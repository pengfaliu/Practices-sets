#include <stdio.h>

int main()
{
    int eof = EOF;
    printf ("getchar() != EOF value is: %d\n",getchar() != eof);
    printf ("EOF value is: %d\n",eof);
}
