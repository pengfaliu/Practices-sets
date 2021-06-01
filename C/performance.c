/*
todo 更改为多线程
*/
#include <stdio.h>

#define MIN 1
#define MAX 100000
int main() {
    long int i;
    long int j;
    long int total;
    for(i = MIN; i<=MAX ; i++ ) {
        for (j= MIN;j<= MAX;j++){
            total += (i+j);
        }
    }
    printf("%ld",total);
    return total;
}
