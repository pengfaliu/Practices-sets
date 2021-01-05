#include <stdio.h>

struct Age {
     unsigned int age:3;
};

int main() {

   struct Age age1;
   struct Age *age_pointer;
   age_pointer = &age1;
   
   age1.age = 4;
   
   printf( "Sizeof( age1 ) : %lu\n", sizeof(age1) );
   printf( "age.age : %d\n", age1.age );

   age1.age = 7;
   
   printf( "Sizeof( age1 ) : %lu\n", sizeof(age1) );
   printf( "age1.age : %d\n", age1.age );

   
   age_pointer -> age = 8; 
   printf( "age_pointer->age sizeof : %lu\n", sizeof(age_pointer) );
   printf( "age_pointer->age : %d\n", age_pointer -> age );

   age_pointer -> age = 18; 
   printf( "age_pointer->age sizeof : %lu\n", sizeof(age_pointer) );
   printf( "age_pointer->age : %d\n", age_pointer -> age );

   return 0;
}
