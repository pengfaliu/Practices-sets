#!/usr/bin/env python
#coding:utf-8
"""
  Author:  lfp --<>
  Purpose: 
  Created: 03/02/21
"""

#import unittest
import copy 

#----------------------------------------------------------------------
def heavenlyAndEarthly():
    """
    打印出天干地支一甲子
    """
    i_counts = 0;
    
    heavenlyStems = ['甲','乙','丙','丁','戊','己','庚','辛','壬','癸'];
    earthlyBranches = ['子','丑','寅','卯','辰','巳','午','未','申','酉','戌','亥'];

    tmph = copy.copy(heavenlyStems); #建立天干栈
    tmpe = copy.copy(earthlyBranches); #建立地支栈

    
    while (True):
        
        if len(tmph) != 0:
            h = tmph.pop(0);
        else:
            tmph = copy.copy(heavenlyStems);
            h = tmph.pop(0);
           
        if len(tmpe) != 0:
            e = tmpe.pop(0);
        else:
            tmpe = copy.copy(earthlyBranches);
            e = tmpe.pop(0);

        i_counts += 1;    

        print("%s: %s" % (i_counts,h+e));
        if len(tmph)==0 and len(tmpe) == 0:
            break;
        
       


if __name__ == '__main__':
    #unittest.main()
    heavenlyAndEarthly()