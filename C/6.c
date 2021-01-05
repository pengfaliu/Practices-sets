#include <stdlib.h>  
#include <stdio.h>
 
// 回调函数
void populate_array(int *array, size_t arraySize, int (*getNextValue)(int))
{
    for (size_t i=0; i<arraySize; i++)
        array[i] = getNextValue(n);
}
 
// 获取随机值
int getNextRandomValue(int n)
{
    return rand()%n;
}
 
int main(void)
{
    int myarray[10];
    int (*getNextValue) (int) = &getNextRandomValue;
    /* getNextRandomValue 不能加括号，否则无法编译，因为加上括号之后相当于传入此参数时传入了 int , 而不是函数指针*/
    populate_array(myarray, 10, getNextValue(10));
    for(int i = 0; i < 10; i++) {
        printf("%d ", myarray[i]);
    }
    printf("\n");
    return 0;
}
