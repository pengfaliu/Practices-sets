#include <stdio.h>

int main()
{
	char pattern[] = "ould";
	int i;
	for (i=0;i<4;i++)
        {
            printf("%d = %c\n",i,pattern[i]);
	}
	return 0;
}
