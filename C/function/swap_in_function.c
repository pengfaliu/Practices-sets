#include <stdio.h>
int main()
{
    int tmp,a,b;
    a=10;
    b=100;
    printf("before:&a is %p\n",&a);
    printf("before:&b is %p\n",&b);

    tmp = a;
    a = b ;
    b = tmp;

    printf("after:&a is %p\n",&a);
    printf("after:&b is %p\n",&b);
    printf ("a is %d\n,b is %d\n :",a,b);
}
