#!/usr/bin/env python
#coding:utf-8
"""
  Author:  lfp --<>
  Purpose: performance test 
  Created: 02/10/21
"""

#----------------------------------------------------------------------
def main():
    """
    todo 更改为多线程
    """
    t = 0;
    for i in xrange(1,100001):
        for j in xrange(1,100001):
            t += (i+j);
    
    return t;



if __name__ == '__main__':
    print(main());