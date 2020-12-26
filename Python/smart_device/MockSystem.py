#!/usr/bin/python
# -*- coding: UTF-8 -*-
#-------------------------------------------------------------------------------
# Name:        mocksystem.py
# Purpose:     interface for robot that is openfire client.
# Author:      liufapeng
# Email:       pengfaliu@163.com
# Created:     25/12/2016  d/m/y
# Copyright:   (c) liufapeng 2016
# requirement: python >=2.6
# verion : 1.0.0
# Licence:     GPL V2
#-------------------------------------------------------------------------------


class MockStatus(object):
    
    def __init__(self):
        pass
    
    def mock_television(self,order_num,ip="1.1.1.1"):
        orders = { hex(3856):"电视设备 %s 运行正常，当前播放电影频道" % ip,
               hex(240):"电视设备 %s 己开启系统" % ip,
               hex(241):"电视设备 %s 已关闭系统" % ip,
               hex(242):"电视设备 %s 己静音" % ip,
               hex(243):"电视设备 %s 己取消静音" % ip,
               hex(244):"电视设备 %s 音量加1" % ip,
               hex(245):"电视设备 %s 音量减1" % ip,
               hex(246):"电视设备 %s 频道+1" % ip,
               hex(247):"电视设备 %s 频道-1" % ip,
               hex(1):"电视设备 %s 跳转至45频道" % ip
               }
    
        return orders[order_num]
    
    def mock_aircondition(self,order_num,ip="2.2.2.2"):
        orders = { hex(3856):"空调设备 %s 运行正常，温度25度，温度50%%" % ip,
               hex(240):"空调设备 %s 己开机" % ip,
               hex(241):"空调设备 %s 已关机" % ip,
               hex(242):"空调设备 %s 制冷开启" % ip,
               hex(243):"空调设备 %s 制热开启" % ip,
               hex(244):"空调设备 %s 温度增加1度" % ip,
               hex(245):"空调设备 %s 温度降低1度" % ip,
               hex(246):"空调设备 %s 上下扫风开启" % ip,
               hex(247):"空调设备 %s 左右扫风开启" % ip

               }
        return orders[order_num]
    
    def mock_refrigerator(self,order_num,ip="3.3.3.3"):
        orders= { hex(3856):"冰箱设备 %s 运行正常，冷冻:-18度，冷藏：5度" % ip,
                  hex(240):"冰箱设备 %s 已开启冷冻系统" % ip,
                  hex(241):"冰箱设备 %s 己关闭冷藏/冻系统" % ip,
                  hex(242):"冰箱设备 %s 冷冻开启" % ip,
                  hex(243):"冰箱设备 %s 冷藏开启" % ip,
                  hex(244):"冰箱设备 %s 温度调高1度" % ip,
                  hex(245):"冰箱设备 %s 温度调低1度" % ip
    
                  }  
        
        return orders[order_num]
    
    
if __name__ == "__main__":
    
    t = MockStatus()
    print t.mock_aircondition(hex(3856))
    print t.mock_refrigerator(hex(243))
    print t.mock_television(hex(241))
