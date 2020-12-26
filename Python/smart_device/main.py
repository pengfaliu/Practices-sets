#!/usr/bin/python
# -*- coding: UTF-8 -*-
#-------------------------------------------------------------------------------
# Name:        main.py
# Purpose:     interface for robot that is openfire client.
# Author:      liufapeng
# Email:       pengfaliu@163.com
# Created:     25/12/2016  d/m/y
# Copyright:   (c) liufapeng 2016
# requirement: python >=2.6
# verion : 1.0.0
# Licence:     GPL V2
#-------------------------------------------------------------------------------



import tornado.ioloop
import tornado.web
import control_device
import json
import logging


class MainHandler(tornado.web.RequestHandler):
    t = {'television':hex(1),"refrigerator":hex(2),"aircondition":hex(3)}
    def get(self):
        self.write("Hello, world")

    def post(self):
        device_type = self.get_argument("device_type")
        orders = eval(self.get_argument("order"))
        
        #logging.info("parameter got over %s,%s" % device_type,orders)
    
        
        control = control_device.Operator()
        result = control.operate(device_type=hex(1),ordername="open")
        self.write(result)
        print device_type
        print orders
        
        if device_type == u'television':
            result = control.operate(device_type=t['television'],ordername=orders.keys())
            print result
            self.write(result)
        if device_type == u"refrigerator":
            result = control.operate(device_type=t['refrigerator'],ordername=orders.keys())
            self.write(result)
        if device_type == u"aircondition":
            result = control.operate(device_type=t['aircondition'],ordername=orders.keys())
            self.write(result)
        else:
            "no device"


application = tornado.web.Application([(r"/control",MainHandler),
                                               ])


if __name__ == "__main__":
    try:
        application.listen(8888)
        tornado.ioloop.IOLoop.instance().start()
    except KeyboardInterrupt:
        print "exit."
    
