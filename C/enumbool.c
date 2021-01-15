#include <stdlib.h>
#include <stdio.h>


int main(void) {
    enum bool
    {
        False, True
    };

    if (False == 0){
        printf ("False value is :%d\n",False);
    }
    if(True == 1) {
        printf ("True value is :%d\n",True);
    }

    return EXIT_SUCCESS;
}