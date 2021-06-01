/* array pointer test*/

#include <stdio.h>

int main() {
    int array[] = {1,2,3,4};
    printf ("array physical addres is: %p\n",&array);
    printf ("array[0] physical addris is: %p\n",&array[0]);
    printf ("array[0] = %d\n",array[0]);

    int *p = &array[4];
    int *pa = &array[0];

    if (p == pa) {
        printf ("ok\n");
    } else if (*p == *pa )
    {
        printf ("okk\n");
    }
    
    return 0;
}