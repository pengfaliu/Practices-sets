/***************************************************
##filename      : cast.c
##author        : liufapeng
##e-mail        : pengfaliu@163.com
##create time   : 2019-11-21 23:30:18
##last modified : 2019-11-21 23:30:33
##description   : NA
***************************************************/
#include <stdio.h>

typedef unsigned char *byte_pointer;

void show_bytes(byte_pointer start, int len) {
    int i;
    for (i=0;i<len;i++){
        printf("%.2x",start[i]);
        printf("\n");
    }
    printf("----%d----\n",len);
}


void show_int(int x) {
    show_bytes((byte_pointer) &x, sizeof(int));
    }

void show_float(float x) {
    show_bytes((byte_pointer) &x, sizeof(float));
    }
void show_pointer(void *x) {
    show_bytes((byte_pointer) &x, sizeof(void *));
    }

int  main(void) {
    int a = 100;
    float b = 100.1;
    int x = 65535;
    show_int(a);
    show_float(b);
    show_pointer(x);
    return 0;
    }