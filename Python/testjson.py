#----------------------------------------------------------------------
#!/usr/bin/env python
#coding:utf-8
"""
  Author:   --<>
  Purpose: 
  Created: 01/19/21
"""
import json

#----------------------------------------------------------------------
def jsonread(string):
    """"""

    print(json.loads(string))  
    
    

if __name__ == '__main__':
    x = '{ "msgtype": "markdown", "markdown": { "content": "<@all>疫情无情小妹有情,马上要下班了哦,疫情期间请<font color=\"warning\">小哥哥，小姐姐们，不要忘记带以下物品哦<//font>:1. <font color=\"info\">笔记本电脑+电源<//font> 2. <font color=\"info\">测试手机+生产手机<//font> 3. <font color=\"info\">All your belongings.<//font>" } }'
    
    jsonread(x)
    #""""""