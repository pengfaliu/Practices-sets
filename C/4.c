#include <stdio.h>
 
const int MAX = 3;
 
int main ()
{
   float  var[] = {10.0, 100.1, 200.2,102.2};
   float  *ptr;
   int i,MAX;
   
   printf("sizeof(var) is %lu\n",sizeof(var)); 
   MAX = sizeof(var)/4;
 
   /* 指针中的数组地址 */
   ptr = &var[MAX-1];
   printf("var 地址是: %p\n",ptr);
   for ( i = MAX; i > 0; i--)
   {
 
      printf("存储地址：var[%d] = %p\n", i, ptr );
      printf("存储值：var[%d] = %f\n", i, *ptr );
      printf("每个浮点数占用大小 %lu\n",sizeof(*ptr));
 
      /* 指向下一个位置 */
      ptr--;
   }
   return 0;
}
