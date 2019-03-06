#!/usr/bin/python
#coding:utf8
#decorator script
import datetime
import functools

def logs(func):
    @functools.wraps(func)
    def wrapper(*args,**kw):
        print 'call %s():' % func.__name__
        return func(*args,**kw)
    return wrapper

def logs1(text):
    def decorator(func):
        @functools.wraps(func)
        def wrapper(*args, **kw):
            print '%s %s():' % (text, func.__name__)
            return func(*args, **kw)
        return wrapper
    return decorator
    
@logs1("liufapeng")
def now():
    print datetime.datetime.now()
    
if __name__ == "__main__":
    now()
    print now.__name__


        