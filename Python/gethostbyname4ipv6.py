#!/bin/env python
#coding:utf8

#get ipv6

import socket
result = socket.gethostbyname("IPv6test.ntes53.netease.com")
print(result)


result = socket.getaddrinfo("IPv6test.ntes53.netease.com", 0, socket.AF_INET6) 
print result 
result = socket.getaddrinfo("IPv6test.ntes53.netease.com", 0, socket.AF_INET) 
print result 
result = socket.getaddrinfo("IPv6test.ntes53.netease.com", 0, socket.AF_UNSPEC) 
print result 



import requests 
response = requests.get("http://IPv6test.ntes53.netease.com:8000", stream=True) 
print response.raw._fp.fp._sock.getpeername() 