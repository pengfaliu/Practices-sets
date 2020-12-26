#!/usr/bin/env python 
#coding:utf-8

class  MyException(Exception):
    "exception by me "
    def __init__(self,big,small):
        Exception.__init__(self)
        self.big = big
        self.small = small
        
        
def raisetst():
    try:
        s = raw_input("请输入: ")
        if s <=10:
            raise MyException(s, 11)
        

    except EOFError:
        print "你输入了一个结束标记"
    except MyException,x:
        print str(x)
        print "输入的是%d, 最小应该是%d" % (x.big,x.small)        
    else:
        print "no error"
        

if __name__ == "__main__":
    raisetst()