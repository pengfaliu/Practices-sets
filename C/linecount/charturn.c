#include <stdio.h>
 
int main(void)
{
      int c;
      int inspace;
         
      while((c = getchar()) != EOF)
      {
      if(c == '\t')
      {
            /* c = '\t';
            putchar (c); */
            printf ("\\t");
      }
       
      /* We haven't met 'else' yet, so we have to be a  * little clumsy */
      if(c == '\b')
        {
           /* c = '\b';
            putchar (c);
            */
            printf ("\\b");
        }

      if ( c == '\\' )
      {
            /* c = '\\';
            putchar (c);
            */
          printf ("\\");
      }
          putchar (c);
      }
       return 0;
}
