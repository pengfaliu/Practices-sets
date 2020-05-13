#include <stdio.h>

void get_global(lua_State *L)
{
    int global_var1;
    lua_getglobal(L, "global_var1"); /* 从lua的变量空间中将全局变量global_var1读取出来放入虚拟堆栈中 */
    global_var1 = lua_tonumber(L, -1); /* 从虚拟堆栈中读取刚才压入堆栈的变量，-1表示读取堆栈最顶端的元素 */

    printf("Read global var from C: %d\n", global_var1);
}
