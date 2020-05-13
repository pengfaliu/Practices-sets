#!/usr/bin/python


def factorial1(x):
    if x == 0:
       return 1
    else:
      return x*factorial1(x-1)


print(factorial1(10))
print(factorial1(5))


f2 = factorial1(5)

print(f2)



f3 = factorial1

print(f3(10))
