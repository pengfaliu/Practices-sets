#/usr/bin/python
#coding:utf8
# 
class A(object):
    def add(self, x):
        y = x+100
        print(y)
class B(A):
    def add(self, x):
        super(B,self).add(x)
        
if __name__ == "__main__":
    b = B()
    b.add(2)  