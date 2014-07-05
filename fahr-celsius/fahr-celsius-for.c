#include <stdio.h>

#define LOWER 0
#define UPPER 300
#define STEP 20

char style()
{
    int count = 15;
    int i = 0;
    printf ("%s\t%s\n","fahr","celsius");
    for (i=0;i<=count;i++)
    {
        printf ("%s","-");
    }
    printf ("\n");
}

int main ()
{
    int fahr;
    style();
    for ( fahr=UPPER; fahr >= LOWER; fahr =fahr - STEP)
    {
        printf ( " %3d\t%6.1f\n", fahr, (5.0/9.0)*(fahr-32.0));
    }
}
