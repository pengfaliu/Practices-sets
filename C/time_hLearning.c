#include <stdio.h>
#include <time.h>
#include <stdlib.h>
 
int main(void)
{
   clock_t start_t, end_t;
   double total_t;
   int i;
 
   start_t = clock();
   printf("程序启动，start_t = %ld\n", start_t);
    
   printf("开始一个大循环，start_t = %ld\n", start_t);
   for(i=0; i< 10000000; i++)
   {
      for(i=0; i< 10000000; i++) {
          printf("i");
      } 
   }
   end_t = clock();
   printf("\n大循环结束，end_t = %ld\n", end_t);
   
   total_t = (double)(end_t - start_t) / CLOCKS_PER_SEC;
   printf("CPU 占用的总时间：%f\n", total_t  );
   double xt ;
   xt = (double)(end_t - start_t) / total_t;
   printf("CLOCKS_PER_SEC's value: %f\n", xt  );
   printf("程序退出...\n");
 
   return EXIT_SUCCESS;
}