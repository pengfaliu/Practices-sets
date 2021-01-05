#include <stdio.h>
 
enum DAY
{
      MON=1, TUE, WED, THU, FRI, SAT, SUN
};
 
int main()
{
    enum DAY day;
    int *p; 
    int k = 1;
    int *x = NULL;
    day = WED;
    p = &k;
    printf("value of day :%d\n",day);
    printf("&day pointer: %p\n",&day);
    printf("p pointer: %p\n",p);
    printf("*p pointer: %d\n",*p);
    printf("x pointer: %p\n",x);

    p++;
    x--; 
    printf("p++ pointer: %p\n",p);
    printf("*p pointer: %d\n",*p);
    printf("x-- pointer: %p\n",x);
    printf("*x pointer: %d\n",*x);
    return 0;
}
