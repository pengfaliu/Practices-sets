#include <stdio.h>
 
enum DAY
{
      MON, TUE, WED, THU, FRI, SAT, SUN
} day;
 
int main()
{
    
    day = WED;
    printf("%d",day);
    return 0;
}
