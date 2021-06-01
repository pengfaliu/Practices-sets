#include <iostream>
using namespace std;


namespace test1 {
    void func() {
        cout << "firt namespace in  test1" << endl;
    }
}

namespace test2
{
    void func() {
        std::cout << "second namespace in test2" << std::endl;
    }
}

int main(int argc, const char** argv) {
    test1::func();
    test2::func();
    return 0;
}