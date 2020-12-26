#!/usr/bin/python
# -*- coding: UTF-8 -*-
#-------------------------------------------------------------------------------
# Name:        device_type.py
# Purpose:     define device types.
# Author:      liufapeng
# Email:       pengfaliu@163.com
# Created:     25/12/2016  d/m/y
# Copyright:   (c) liufapeng 2016
# requirement: python >=2.6
# verion : 1.0.0
# Licence:     GPL V2
#-------------------------------------------------------------------------------


class  DeviceType(object):
    def __init__(self,default_device=None):
        self.television = hex(1)
        self.refrigerator = hex(2)
        self.aircondition = hex(3)
        self.others = "device has not been support!"

    def television(self):
        return self.television
    
    def refrigerator(self):
        return self.refrigerator
    
    def aircondition(self):
        return self.aircondition
    
    def others(self):
        return self.others
    
if __name__ == "__main__":
    
    t = DeviceType()
    
    print t.television    
    print t.refrigerator
    print t.aircondition
    
        