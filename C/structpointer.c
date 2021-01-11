#include <stdio.h>
#include <errno.h>


#define SUCCESS 0 

typedef struct x {

      int t;
      int k;
} x;



int main(void) {
    x *d;
    d->t=100;
    d->k = 1000;
 
    printf("d->t :%d\n ", d->t);
    printf("d->k :%d\n ", d->k);
    return SUCCESS;
}

