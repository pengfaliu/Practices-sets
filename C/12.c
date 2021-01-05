#include <stdio.h>

struct Books {
    char title[50];
    char author[50];
    char subject[100];
    int book_id;
} ;

struct Books book = {"C语言","lfp","PROGRAMMING",123456};
struct Books book1 = {"IC语言","lfp","PROGRAMMING",223456};

int main () {

    printf("title: %s\nauthor: %s\nsubject: %s\nbook_id: %d\n",book.title, book.author,book.subject,book.book_id);
    printf("title: %s\nauthor: %s\nsubject: %s\nbook_id: %d\n",book1.title, book1.author,book1.subject,book1.book_id);
    return 0;
}
