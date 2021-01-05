#include <stdio.h>
#include <string.h>
 
int main ()
{
   char str1[14] = "runoob";
   char str2[14] = "google";
   char str3[14];
   int  len ;
 
   /* 复制 str1 到 str3 */
   strcpy(str3, str1);
   printf("strcpy( str3, str1) :  %s\n", str3 );
 
   /* 连接 str1 和 str2 */
   strcat( str1, str2);
   printf("strcat( str1, str2):   %s\n", str1 );
 
   /* 连接后，str1 的总长度 */
   len = strlen(str1);
   printf("strlen(str1) :  %d\n", len );
   printf("sizeof(str1) :  %lu\n", sizeof(str1)/1-1 );

   char str4[] = "I like English";
   
   printf("strlen(str4) :  %d\n", strlen(str4) );
   printf("sizeof(str4) :  %lu\n", sizeof(str4)/1-1);

 
   return 0;
}
