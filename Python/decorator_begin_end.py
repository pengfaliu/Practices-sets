#!/usr/bin/python
#coding:utf8
#decorator script begin and end show:
import functools
import datetime as d

def logsa(func):
    @functools.wraps(func)
    def wrapper(*args,**kw):  
        print 'begin call'
        print " call %s()" % func.__name__
        print "end call"
        return func(*args,**kw)
    return wrapper

def logsb(text):
    def decorator(func):
        @functools.wraps(func)
        def wrapper(*args,**kw):
            print 'begin call'
            print " %s call %s()" % (text,func.__name__)
            print "end call"
            return func(*args,**kw)
        return wrapper
    return decorator

@logsa 
def helloword():
    print "helloword, this is a decorator"
    print '*' * 8

@logsb("execute")
def now():
    print d.datetime.now()


    

if __name__ == "__main__":
    helloword()
    print "function name is: %s " % helloword.__name__
    print '-'*40
    now()
    print "function name is: %s " % now.__name__