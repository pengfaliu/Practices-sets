#include <stdio.h>


int main() {

   long long int a;
   long int b;
   int c;
   a = 0;
   b = 0;
   c = 0;
   printf("a=%luB,b=%ldB,c=%luB\n", sizeof(a),sizeof(b),sizeof(c));
   return 0;
}
