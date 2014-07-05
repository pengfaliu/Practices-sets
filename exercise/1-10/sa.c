#include <stdio.h>

int help()
{
    printf (" -------------------\n");
    printf ("| Ctrl+D show answer |\n\
| Ctrl+C exit        |\n");
    printf (" -------------------\n");
    printf ("\ntab display \\t \n '\\' display '\\\\' \nbackspace display \\b\n");
    return 0;
}

int main ()
{

    int c;
    help();

    while ( ( c =getchar() ) != EOF )
    {
        if ( c == '\t')
        {
           printf ("\\t");
        }
        else if ( c == '\b' )
        {
            printf ("\\b");
        }
        else if ( c == '\\')
        {
            printf ("\\\\");
        }
        else 
        {
            putchar( c );
        }

    }
    return 0;
}
