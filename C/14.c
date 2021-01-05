#include <stdio.h>
#include<string.h>

struct Books {
    char title[50];
    char author[50];
    char subject[100];
    int book_id;
} ;


void printBooks(struct Books *book) {
     
    printf("title: %s\n",book -> title);
    printf("author: %s\n",book -> author);
    printf("subject: %s\n",book -> subject);
    printf("book_id: %d\n",book -> book_id);
	
}


int main () {
   struct Books Book2 ;
   struct Books Book1 ;

     /* Book1 详述 */
   strcpy( Book1.title, "C Programming");
   strcpy( Book1.author, "Nuha Ali"); 
   strcpy( Book1.subject, "C Programming Tutorial");
   Book1.book_id = 6495407;
 
   /* Book2 详述 */
   strcpy( Book2.title, "Telecom Billing");
   strcpy( Book2.author, "Zara Ali");
   strcpy( Book2.subject, "Telecom Billing Tutorial");
   Book2.book_id = 6495700; 

   printBooks(&Book2);
   printBooks(&Book1);
   return 0;
}
