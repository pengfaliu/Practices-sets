//
//  declare_extern.c
//  
//
//  Created by liufapeng on 2019/3/13.
//

#include "declare_extern.h"
/*定义两个全局变量*/
int x=10;
int y=20;
int add2num();
int main(void)
{
    int result;
    result = add2num();
    printf("\a result 为: %d\n",result);
    //测试警报铃声
    printf("\a");
    printf("\b");
    
    return 0;
}
