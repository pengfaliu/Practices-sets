#!/usr/bin/env python 
#coding:utf8 

import requests
import base64
import hashlib
import os


#参数bot是指机器人的key，具体可参考企业微信机器人官网
#发送普通消息，text是文本内容
def send_text(text,bot):
    url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key="+bot;
    headers = {"Content-Type": "text/plain"}
    data = {
          "msgtype": "text",
          "text": {
             "content": text,
          }
       }
    r = requests.post(url, headers=headers, json=data)
    print(r.text)
#发送markdown消息，text是文本内容，可接受markdown语法
def send_markdown(text,bot):
    url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key="+bot
    data = {
    "msgtype": "markdown",
    "markdown": {
        "content": text
    }}
    r = requests.post(url,json=data)
    print(r.text)
#发送文件，file_path是指文件路径(我目前就试了excel文件)
def send_file(file_path,bot):
    file_url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/upload_media?key="+bot+"&type=file"
    file= {'file':open(file_path,'rb')}
    result = requests.post(file_url, files=file)
    file_id = eval(result.text)['media_id']
    url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key="+bot
    data = {
        "msgtype": "file",
        "file": {"media_id": file_id,}
    }
    r = requests.post(url, json=data)
    print(r.text)
#发送图片，file_path是指图片路径
def send_img(file_path,bot):
    with open(file_path,"rb") as f:
        base64_data = base64.b64encode(f.read())
    file = open(file_path, "rb")
    md = hashlib.md5()
    md.update(file.read())
    res1 = md.hexdigest()
    url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key="+bot
    headers = {"Content-Type": "text/plain"}
    data = {
            "msgtype": "image",
            "image": {
                "base64": base64_data.decode('utf-8'),
                "md5": res1
            }
        }
    r = requests.post(url, headers=headers, json=data)
    print(r.text)
    
if __name__ == '__main__':
    send_text("hello", "1a67ada9-5b12-4272-bf19-ae654765e19a")