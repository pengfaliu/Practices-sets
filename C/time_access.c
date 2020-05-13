#include <stdio.h>
#include <time.h>
#include <string.h>

int  main() 
{
    struct tm t;
    t.tm_sec    = 10;
    t.tm_min    = 10;
    t.tm_hour   = 16;
    t.tm_mday   = 15;
    t.tm_mon    = 0;
    t.tm_year   = 120;
    t.tm_wday   = 3;

    
    printf("the time is: %s",asctime(&t));
    
    time_t now;        //声明time_t类型变量
    time(&now);        //获取系统日期和时间
    printf("the time is now: %ld\n", time(&now));
    printf("now the localtime is: %s",asctime(localtime(&now)));
    return 0;
}

/*
 #include <stdio.h>
 #include <time.h>

 #define TIME_MAX 32

 void get_time(void);

 int main()
 {
   get_time();
   getchar();
   return 0;
 }

 void get_time(void)
 {
   time_t now;
   time(&now);

   // 定义两个变量，存储转换结果
   struct tm tmTmp;
   char stTmp[TIME_MAX];

   // 转换为tm结构
   localtime_s(&tmTmp,&now);

   // 转换为字符串并输出
   asctime_s(stTmp,&tmTmp);
   printf("Current time is: %s\n",stTmp);
 }
 */
