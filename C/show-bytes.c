/***************************************************
##filename      : show-bytes.c
##author        : liufapeng
##e-mail        : pengfaliu@163.com
##create time   : 2019-11-21 23:59:18
##last modified : 2019-11-21 23:59:33
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

void test_show_bytes(int val) {
    int ival = val;
    float fval = (float) ival;
    int *pval = &ival;
    show_int(ival);
    show_float(fval);
    show_pointer(pval);
    }

int main(void) {
    int a = 12345;
    test_show_bytes(a);
    return 0;
    }