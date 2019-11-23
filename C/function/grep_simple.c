//
//  grep_simple.c
//  
//
//  Created by liufapeng on 2019/3/16.
//

#include <stdio.h>
#define MAXLINE 100 /* The MAX length of input */

int getlinenew(char line[],int max);
int strindex(char source[], char searchfor[]);

char pattern[] = "ould"; /* pattern for find*/

/* 找出所有与模式匹配的行 */

int main()
{
    char line[MAXLINE];
    int found = 0;
    
    while (getlinenew(line,MAXLINE) >0)
    {
        if (strindex(line,pattern)>=0) {
            printf("%s",line);
            found++;
        }
    }
    return found;
}

// getline：取一行放到s中，并返回该行的长度

int getlinenew(char s[], int lim)
{
    int c,i;
    i = 0;
    while (--lim >0 && (c=getchar()) != EOF && c != '\n') {
        s[i++] = c;
        if ( c == '\n')
        {
            s[i++] = c;
        }
    }
    s[i] = '\0';
    return i;
}

/* strindex: 返回t在s中的位置，若未找到则返回-1 */


int strindex(char s[],char t[])
{
    int i,j,k;
    for (i=0;s[i] != '\0'; i++)
    {
        for (j=i,k=0;t[k]!='\0' && s[j] == t[k];j++,k++)
            if (k>0 && t[k] == '\0')
            {
                return i;
            }
    }
    return -1;
}
