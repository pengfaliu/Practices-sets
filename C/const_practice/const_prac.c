//
//  const_prac.c
//  
//
//  Created by liufapeng on 2019/3/13.
//

#include "const_prac.h"
int main (void)
{
    const int LENGTH = 10;
    const int WIDTH = 100;
    const char NEWLINE = '\n';
    
    int area ;
    area = LENGTH * WIDTH;
    printf("value of aera is :%d",area);
    printf("%c", NEWLINE);
    return 0;
}
