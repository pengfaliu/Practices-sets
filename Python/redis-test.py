#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author : liufapeng
# date : 2019-12-26
# name : redis-test


import redis
import time

########################################################################
class RedisUtil(object):
    """redis test"""
    
    #----------------------------------------------------------------------
    def __init__(self):
        """Constructor"""
        self.default_host = '127.0.0.1'
        self.default_port = 6379
        try:
            self.handler = redis.Redis(host=self.default_host,port=self.default_port,socket_timeout=7, socket_connect_timeout=3, socket_keepalive=None,encoding='utf-8')
        except redis.exceptions.ConnectionError as e:
            print(e)
    def StringOps(self,name,value):
        try:
            if self.handler.set(name, value, ex=10):
                print("insert successfull")
        except redis.exceptions.ResponseError as e:
            print(e)
        print("%s\n" % self.handler.get(name))
        print("11秒后....")
        for i in xrange(0,11):
            time.sleep(1)
            print(i)
        
        print("wow,the key gone ... \n %s\n" % self.handler.get(name))

    def HashOps(self,name,**keys):
        try:
            self.handler.hmset(name)
        except redis.exceptions.ResponseError as e:
            print(e)
        except redis.exceptions.RedisError as e:
            print(e)
            
        print("%s\n" % self.handler.hmget(name, keys))
        
if __name__ == "__main__":
    r = RedisUtil()
    r.StringOps('name','刘发鹏')
    r.HashOps("hashtest",{"k1":1,"k2":2,"k3":3})
    #r.HashOps("hasttest")
    