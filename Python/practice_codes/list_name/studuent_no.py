#!/usr/bin/env python 
# coding:utf-8
old_list_file='/Users/huamanda/Documents/code/python-code/list_name/3.txt'
new_list_file='/Users/huamanda/Documents/code/python-code/list_name/2.txt'

old_f = open(old_list_file)
old_f_context=old_f.readlines()
old_f.close()

new_f = open(new_list_file)
new_f_context=new_f.readlines()
new_f.close()


new_tmp=list()  
old_tmp=list()

for i in new_f_context:
    n_tmp = tuple(i.rstrip().split())
    new_tmp.append(n_tmp)


for i in old_f_context:
    o_tmp = tuple(i.rstrip().split())
    old_tmp.append(o_tmp)

old_tmp.sort(key=lambda x:x[1])     #列表元素中元组以第一个元素排序
    
for i in new_tmp:
    for j in old_tmp:
        if i[1] == j[1]:
            print "%s %s %s %s" % (i[0],j[0],i[1],j[1])
            break
        if i[1] not in [x[1] for x in old_tmp]: #如果新的手机号不在老的里面，输出该手机号
            print  "err,%s %s"  % (i[0],i[1])
            break

        

