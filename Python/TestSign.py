#!/usr/bin/env python
#coding :utf8


########################################################################
class TestSign:
    """"""

    #----------------------------------------------------------------------
    def __init__(self):
        """Constructor"""
        
    
    #----------------------------------------------------------------------
    def sumtest( arg1, arg2 ):
        # 返回2个参数的和."
        total = 0
        total = arg1 + arg2
        print "函数内 : ", total
        return total
    
    
if __name__ == "__main__":    
    a = [100,102,105,234,900]
    t = 0
    for i in a:
        t += i
        
    print(t)
    print(t/len(a))
    print((t >> 2)- 1)
    
        
        
    
        
    
        