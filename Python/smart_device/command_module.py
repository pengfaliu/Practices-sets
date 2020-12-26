#!/usr/bin/env python
#coding:utf-8

import tornado.ioloop
import tornado.web
from tornado.options import define,options,parse_command_line
import json

class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.write("Hello, world")
    def post(self):
        device_type = self.get_arguments("device_type")
        orders = json.loads(self.get_arguments("order"))
        print orders
        print(self.request.remote_ip)
        print device_type[0]
        
        #拆分指令命令
        operation = orders.keys()[0].encode("utf8")
        command = orders[operation].encode("utf8")
        
        if device_type[0] == "television"  and  operation == "open" and  command == "F0":
            self.write("成功接收")
            self.write("电视已经打开，目前是湖南卫视频道，节目是快乐大本营")
        else:
            self.write("其他功能还未完善")
        #self.write(str(device_type))

application = tornado.web.Application([(r"/control",MainHandler),
                                       ])

if __name__ == "__main__":
    try:
        application.listen(8888)
        tornado.ioloop.IOLoop.instance().start()
    except KeyboardInterrupt:
        print "exit."