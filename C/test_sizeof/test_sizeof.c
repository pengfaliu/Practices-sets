//
//  test_sizeof.c
//  
//
//  Created by liufapeng on 2019/3/13.
//

#include "test_sizeof.h"
int main()
{
    printf ("int store size is :%lu \n",sizeof(int));
    printf ("fload store size is :%lu \n",sizeof(float));
    printf ("long store size is :%lu \n",sizeof(long));
    printf ("dobule store size is :%lu \n",sizeof(double));
    printf ("long dobule store size is :%lu \n",sizeof(long double));
    printf ("long in store size is :%lu \n",sizeof(long int));
    return 0;
}
