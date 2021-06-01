/*
todo 更改为多线程
*/

#include <iostream>
using namespace std;

#define MIN 1
#define MAX 100000

int main() {
    long int i;
    long int j;
    long int total;
    for(i = MIN; i<=MAX ; i++ ) {
        for (j= MIN;j<= MAX;j++){
            total += (i+j);
        }
    }
    cout  << total << endl;
    return total;
}