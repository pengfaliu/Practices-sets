#include <stdio.h>

#define IN 1  /* 在单词内*/
#define OUT 0  /* 在单词外*/

/* 统计输入的行数 单词数与字符数 */

int main (void)
{
    int c,nl,nw,nc,state;

    /*nc 字符数 */
    /* nw 单词数 */
    /* nl 行数 */

    state = OUT;
    nl = nw = nc = 0;
    while ( (c = getchar () ) != EOF ) {
        ++nc;
        if ( c == '\n' )
        {
            ++nl;
        }

        if ( c == ' ' || c == '\n' || c == '\t' )
        {
            state = OUT;
        }
        else if (state == OUT)
        {
            state = IN;
            ++nw;
        }
    }
    printf  ("line count:%d\n words count:%d\n character count: %d\n"
            ,nl,nw,nc);
}
