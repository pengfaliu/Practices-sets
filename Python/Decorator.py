#!/usr/bin/env python
#coding: utf-8


def deco(func):
    print("before myfunc() called.")
    func()
    print("  after myfunc() called.")
    print "----"
    return func

@deco

def myfunc():
    print("myfunc() called.")

if __name__ == "__main__":
    deco(myfunc)