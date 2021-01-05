#include <stdio.h>
 
#define TRUE  1
#define FALSE 0

typedef enum Bool 
{
    False,True
} bool;
 
int main( )
{
   bool F;
   bool T;

   F = False;
   T = True;
   printf( "F 的值: %d\n", F);
   printf( "T 的值: %d\n", T);
   
   printf( "TRUE 的值: %d\n", TRUE);
   printf( "FALSE 的值: %d\n", FALSE);
 
   return 0;
}
