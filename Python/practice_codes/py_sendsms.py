#!/usr/bin/python
# -*- coding: utf-8 -*-
#-------------------------------------------------------------------------------
# Name:        query.py
# Purpose:     Send SMS by Zabbix monitor
# Author:      wangbaoshun
# Email:       baoshun.wang@msfinance.cn
# Created:     13/07/2015  d/m/y
# Copyright:   (c) wangbaoshun 2015
# requirement: python >=2.4
# verion : 1.0.0
# Licence:     GPL V2
#-------------------------------------------------------------------------------

import os
import requests
import json
import uuid
import sys
import httplib
import urllib
import smtplib
import logging
from email.mime.text import MIMEText
from email.header import Header


reload(sys)
sys.setdefaultencoding('utf8')


def mylog():
    import logging.config
    gLogger = logging.getLogger()
    '''
    logdir = "log/"
    logfile = "sms.log"
    os.system("mkdir -p " + logdir)
    log_file = "./%s/%s"%(logdir,logfile)
    '''
    #log_file = '/home/finance/Logs/zabbix/sms.log'
    log_file = '/home/zabbix/Logs/sms.log'
    formatter = logging.Formatter('[%(asctime)s][%(levelname)s] %(message)s')
    handler = logging.StreamHandler(sys.stdout)
    handler.setFormatter(formatter)
    gLogger.addHandler(handler)
    formatter = logging.Formatter('[%(asctime)s][%(levelname)s] %(message)s')
    handler = logging.handlers.RotatingFileHandler(log_file)
    handler.setFormatter(formatter)
    gLogger.addHandler(handler)
    gLogger.setLevel(logging.INFO)
    return gLogger
sms_log = mylog()


# Define mail variable
mail_host = 'mail.msxf.com'
mail_user = 'monitor@msxf.com'
#mail_pass = 'p@ssw0rd'
mail_pass = 'd_}IV9lI?|19sf'
mail_postfix = 'msxf.com'
#persons = "baoshun.wang@msxf.com;fapeng.liu@msxf.com"
persons = "baoshun.wang@msxf.com;monitor@msxf.com"
to_list = persons.split(";")
# Send mail function
def send_mail(to_list,subject,content):
    me = mail_user+"<"+mail_user+"@"+mail_postfix+">"
    me = mail_user
    msg = MIMEText(content)
    msg['Subject'] = subject
    msg['From'] = me
    #msg['to'] = ','.join(to_list)
    #msg['to'] = persons
    msg['to'] = to_list

    try:
        s = smtplib.SMTP()
        s.connect(mail_host,587)
        #s.set_debuglevel(1)
        s.starttls()
        s.ehlo()
        s.login(mail_user,mail_pass)
        s.sendmail(me,to_list,msg.as_string())
        s.close()
        return True
    except Exception,e:
        print str(e)
        return False


# Define sms variable
code = str(uuid.uuid1())
sp = code.split('-')
delimiter = ''
SerialNumber = delimiter.join(sp)
#url = "http://192.168.2.41:8240/SMS/sendSingleSMS"
#url = "http://notification.msxf.lotest/SMS/sendSingleSMS"
url = "http://notification.msxf.lo/SMS/sendSingleSMS"
phone_number = str(sys.argv[1])
#subject =  sys.argv[2]
#content =  sys.argv[3]
content = sys.argv[2]

# Define send-sms function
def send_sms():
    try:
        sms_log.info(SerialNumber+'---'+'maintenance'+'---'+'maintenance_Warning'+'---'+phone_number)
        resp = requests.post((url),
        data={
            "SourceSerialNumber": SerialNumber,
            "sourceBizSystem": "maintenance",
            "sourceBizType": "maintenance_Warning",
            "mobileNumber": phone_number,
            #"msgParam": subject+'|'+content,
            "msgParam": content,
            "type": "json"
        },timeout=3 , verify=False);
    except Exception,e:
        #print str(e)
        sms_log.error(str(e))
        return False
    result =  json.loads(resp.content)
    message =  eval(json.dumps(result))
    #logging.info(message)
    #abc = str(message) 
    #sms_log.info(str(message))
    if int (eval(json.dumps(result))['code']) == 0:
        sms_log.info(str(message))
    else:
        send_mail(persons, '短信发送失败',str(message))
        sms_log.error(str(message))
    #    print "send mail"

if __name__ == "__main__":
    send_sms();
