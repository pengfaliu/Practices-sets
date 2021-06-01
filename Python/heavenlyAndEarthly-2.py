#!/usr/bin/env python
#coding:utf-8
"""
  Author:  --<>
  Purpose: 
  Created: 03/08/21
"""

#----------------------------------------------------------------------
def hae():
    """"""
    jia = ['甲','乙','丙','丁','戊','己','庚','辛','壬','癸'];
    zi = ['子','丑','寅','卯','辰','巳','午','未','申','酉','戌','亥'];
    i = len(jia)
    j = len(zi)
    m=0
    n=0
    k=0
    while(True):
        k +=1;
        print("%s:%s" % (k,jia[m]+zi[n]));
        if(m<i-1):
            m +=1;
        else:
            m=0;
         
        if(n<j-1):
            n += 1;
        else:
            n=0;
            if(m==0):
                break;
             
if __name__ == '__main__':
   
    hae()