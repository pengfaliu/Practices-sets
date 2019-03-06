#include <stdio.h>

int main()
{
    printf ("\npress [Ctrl+D] if you want to get answer!\n \
[Ctrl+C] exit!\n");

    int c,spacecount,tabcount,returncount;
    spacecount = tabcount = returncount = 0;

    while ( ( c = getchar () ) != EOF )
    {
        if ( c == '\n' )
        {
            ++returncount;
        }

        if ( c == '\t' )
        {
            ++tabcount;
        }

        if ( c == ' ' )
        {
            ++spacecount;
        }

    }

    printf ("space:%d\n",spacecount);
    printf ("return:%d\n",returncount);
    printf ("tab:%d\n",tabcount);
}
