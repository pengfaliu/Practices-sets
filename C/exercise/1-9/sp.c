#include <stdio.h>

int help()
{
    printf (" -------------------\n");
    printf ("| Ctrl+D show answer |\n\
| Ctrl+C exit        |\n");
    printf (" -------------------\n");
    return 0;
}

int main ()
{

    int c,state,in,out;
    in = 1;
    out = 0;
    state = out;


    help();

    while ( ( c =getchar() ) != EOF )
    {
        if ( c == ' ')
        {
            if ( state == out )
            {
                state = in;
                putchar( c );
            }
        }
        else if ( c != ' ' )
        {
            state = out;
            putchar( c );
        }

    }
    return 0;
}
