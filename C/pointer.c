#include<stdio.h>

char *p;


int main(void)
{
    char a='k';
    p = &a;
    printf("%x,%s,%c\n",*p,p,a);
    return 0;
}

