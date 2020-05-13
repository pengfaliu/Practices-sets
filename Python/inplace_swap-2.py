#!/usr/bin/python

def swap(x,y):
    y = x^y
    x = x^y
    y = x^y
    return (x,y)


if __name__ == '__main__':
    a = 1
    b = 2
    print("a=%d,b=%d" % (a,b))
    print('a=%d,b=%d' % swap(a,b))

