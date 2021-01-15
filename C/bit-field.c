//
//  bit-field.c
//  
//
//  Created by liufapeng on 2021/1/15.
//

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

struct bitfield8byte {
    unsigned int widthValidated;
    unsigned int heightValidated;
};

struct bitfield4byte {
    unsigned int widthValidated:1;
    unsigned int heightValidated:1;
};

struct bitfield8byte b8;
struct bitfield4byte b4;

//define a pointer for bitfield8byte and bitfield4byte;
struct bitfield8byte *b8p;
struct bitfield4byte *b4p;


int main(void) {
    printf ("Memory size ccupied by b8p %lu\n",sizeof(b8p));
    printf ("Memory size ccupied by b4p %lu\n",sizeof(b4p));
    
    printf ("Memory size ccupied by b8 %lu\n",sizeof(b8));
    printf ("Memory size ccupied by b4 %lu\n",sizeof(b4));
    
    b8p = &b8;
    b4p = &b4;

    printf ("Memory size ccupied by b8p %lu\n",sizeof(b8p));
    printf ("Memory size ccupied by b4p %lu\n",sizeof(b4p));
    
    return EXIT_SUCCESS;
}
