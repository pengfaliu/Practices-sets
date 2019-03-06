#!/usr/bin/python
#coding:utf8
#decorator script begin and end show:
import time

class TimeTest(object):
    def __init__(self, hour, minute, second):
        self.hour = hour
        self.minute = minute
        self.second = second

    @staticmethod
    def showTime():
        return time.strftime("%H:%M:%S", time.localtime())
    
if __name__== "__main__":
    print TimeTest.showTime()