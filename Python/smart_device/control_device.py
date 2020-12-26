#!/usr/bin/python
# -*- coding: UTF-8 -*-
#-------------------------------------------------------------------------------
# Name:        control.py
# Purpose:     control smart device, it should be send command and get result that from smart device.
# Author:      liufapeng
# Email:       pengfaliu@163.com
# Created:     25/12/2016  d/m/y
# Copyright:   (c) liufapeng 2016
# requirement: python >=2.6
# verion : 1.0.0
# Licence:     GPL V2
#-------------------------------------------------------------------------------

import DeviceType
import MockSystem

class OrderSet(object):
    """There are sets of device, it descript how to control hardware information."""
    
    def __init__(self):
        pass
    
    def television_set(self,order="status"):
        orders = { "status":hex(3856),
                   "open":hex(240),
                   "close":hex(241),
                   "mute":hex(242),
                   "cancel_mute":hex(243),
                   "volume_HIGH":hex(244),
                   "volume_LOW":hex(245),
                   "Channel_up":hex(246),
                   "Channel_down":hex(247),
                   "Channel_id":hex(1)
                   }
        return orders[order]
    
    def refrigerator_set(self,order="status"):
        orders= { "status":hex(3856),
                   "open":hex(240),
                   "close":hex(241),
                   "quick-freeze":hex(242),
                   "refrigerate":hex(243),
                   "TEMP_HIGH":hex(244),
                   "TEMP_LOW":hex(245)

                   }
        return orders[order]
    
    def air_condition_set(self,order="status"):
        orders = { "status":hex(3856),
                   "open":hex(240),
                   "close":hex(241),
                   "refrigeration":hex(242),
                   "heating":hex(243),
                   "TEMP_HIGH":hex(244),
                   "TEMP_LOW":hex(245),
                   "Sweep_up_down":hex(246),
                   "Sweep_around":hex(247)
                 
                   }
        return orders[order]


class Operator(object):
    """ this class descript how to send command to smart device."""
    

    def __init__(self):

        #实例化 
        self.t = DeviceType.DeviceType()
        self.m = MockSystem.MockStatus()
        self.o = OrderSet()
        self.default_message = "order error."
        self.no_support = self.t.others
   
    def send_order(self,types,order_name):
        """这个函数主要是根据传过来的设备发送相应的十六进制的数字"""
        
        order_num = self.o.television_set(order_name)
        
        if types == self.t.television:
            return self.m.mock_television(order_num, ip="1.1.1.2")
            
        if types == self.t.refrigerator:
            return self.m.mock_refrigerator(order_num, ip="3.3.3.4")
        
        if types == self.t.aircondition:
            return self.m.mock_aircondition(order_num, ip="2.2.2.3")
    
    def operate(self,**kwargs):
        """这个函数主要是判断传过来的参数有哪些"""
        
        if kwargs["device_type"] == self.t.television:
            return self.send_order(self.t.television, kwargs["ordername"])
        
        if kwargs["device_type"] == self.t.refrigerator:
            return self.send_order(self.t.refrigerator,kwargs["ordername"])
        
        if kwargs["device_type"] == self.t.aircondition:
            return self.send_order(self.t.aircondition,kwargs["ordername"])
        
        else:
            
            return self.no_support
        

if __name__ == "__main__":
    
    x = Operator()
    print x.operate(device_type=hex(1),ordername="open")
    print x.operate(device_type=hex(2),ordername="open")
    print x.operate(device_type=hex(3),ordername="open")
    print x.operate(device_type=hex(4),ordername="open")
    
        