#include <stdio.h>

struct Books {
    char title[50];
    char author[50];
    char subject[100];
    int book_id;
} ;


void printBooks(struct Books book) {
     
    printf("title: %s\nauthor: %s\nsubject: %s\nbook_id: %d\n",book.title, book.author,book.subject,book.book_id);
	
}


int main () {
   struct Books book2 = {"C语言","lfp","PROGRAMMING",123456};
   struct Books book1 = {"IC语言","lfp","PROGRAMMING",223456};
   printBooks(book2);
   printBooks(book1);
    return 0;
}
