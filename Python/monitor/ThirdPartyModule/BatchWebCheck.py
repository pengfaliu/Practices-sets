#!/usr/bin/env pyton 
# -*- coding: UTF-8 -*-
#-------------------------------------------------------------------------------
# Name:        Config_ini_Parser.py
# Purpose:     provide mysql's operation.
# Author:      liufapeng
# Email:       pengfaliu@16e.com
# Created:     13/09/2016  d/m/y
# Copyright:   (c) liufapeng 2016
# requirement: python >=2.6
# verion : 1.0.0
# Licence:     GPL V2
#-------------------------------------------------------------------------------

"""

脚本逻辑就是获取站点HEAD，如果正常就返回up，有问题就返回down,getattr()是python内置函数，
接收一个对象，可以根据对象的属性返回对象的值，比较有意思的是compare_site_status()函数中
又定义了一个函数，最后return了这个内部函数，这个函数在map()函数中进行了调用，map()函数接
收2个参数，一个是函数，一个系列，在这个脚本里就是检测每个url的站点状态，如果有问题就发邮件
报警，检测过的站点状态用pickle模块存在data.pkl文件中，在检测站点前先做网络连通性检测，这
里定义的is_internet_reachable()函数实现比较简单，判断是否网络故障就是访问下baidu和qq,
如果这2个站点都不能访问，就表示网络出问题了，不一定是站点出现问题，如果网络正常，然后才进行
站点状态验证。

"""

import pickle, os, sys, logging
from httplib import HTTPConnection, socket
from smtplib import SMTP

def email_alert(message, status):
    fromaddr = 'abc@163.com'
    toaddrs = 'def@abc.com'
    
    server = SMTP('smtp.163.com:25')
    server.starttls()
    server.login('xxxxx', 'xxxx')
    server.sendmail(fromaddr, toaddrs, 'Subject: %s\r\n%s' % (status, message))
    server.quit()

def get_site_status(url):
    response = get_response(url)
    try:
        if getattr(response, 'status') == 200:
            return 'up'
    except AttributeError:
        pass
    return 'down'
        
def get_response(url):
    try:
        conn = HTTPConnection(url)
        conn.request('HEAD', '/')
        return conn.getresponse()
    except socket.error:
        return None
    except:
        logging.error('Bad URL:', url)
        exit(1)
        
def get_headers(url):
    response = get_response(url)
    try:
        return getattr(response, 'getheaders')()
    except AttributeError:
        return 'Headers unavailable'

def compare_site_status(prev_results):
    
    def is_status_changed(url):
        status = get_site_status(url)
        friendly_status = '%s is %s' % (url, status)
        print friendly_status
        if url in prev_results and prev_results[url] != status:
            logging.warning(status)
            email_alert(str(get_headers(url)), friendly_status)
        prev_results[url] = status

    return is_status_changed

def is_internet_reachable():
    if get_site_status('www.baidu.com') == 'down' and get_site_status('www.qq.com') == 'down':
        return False
    return True
    
def load_old_results(file_path):
    pickledata = {}
    if os.path.isfile(file_path):
        picklefile = open(file_path, 'rb')
        pickledata = pickle.load(picklefile)
        picklefile.close()
    return pickledata
    
def store_results(file_path, data):
    output = open(file_path, 'wb')
    pickle.dump(data, output)
    output.close()
    
def main(urls):
    logging.basicConfig(level=logging.WARNING, filename='checksites.log', 
            format='%(asctime)s %(levelname)s: %(message)s', 
            datefmt='%Y-%m-%d %H:%M:%S')
    
    pickle_file = 'data.pkl'
    pickledata = load_old_results(pickle_file)
    print pickledata
        
    if is_internet_reachable():
        status_checker = compare_site_status(pickledata)
        map(status_checker, urls)
    else:
        logging.error('Either the world ended or we are not connected to the net.')
        
    store_results(pickle_file, pickledata)

if __name__ == '__main__':
    main(sys.argv[1:])