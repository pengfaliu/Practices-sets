#include <stdio.h>
#include <string.h>

char style()
{
    int count = 15;
    int i = 0;

    for (i=0;i<=count;i++)
    {
        printf ("%s","-");
    }
    printf ("\n");
}

int main ()
{
    float fahr,celsius;
    int lower,upper,step;

    lower = 0;
    upper = 300;
    step = 20;

    fahr = lower;
    
    printf ("%s\t%6s\n","fahr","celsius");
    style();
    while (fahr <= upper)
    {
        celsius= (5.0 / 9.0 )* (fahr - 32.0);
        printf("%3.0f\t%6.1f\n",fahr,celsius);
        fahr = fahr + step;
    }
}
