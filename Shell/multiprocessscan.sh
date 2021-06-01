#!/bin/bash


tmpfile='./tmp.txt'
Date=`date`


list='a b c d e f g h i j k l m n o p q r s t u v w x y z 0 1 2 3 4 5 6 7 8 9'

#{ 
#for i in $list
#do 
#  dig +nocmd  -t A zjkccb$i.cc +noall +answer
#  dig  +nocmd -t AAAA zjkccb$i.cc +noall +answer
#done
#} &
#
#{
#for i in $list
#  do 
#    for j in $list
#     do 
#         dig +nocmd  -t A zjkccb$i$j.cc +noall +answer
#         dig  +nocmd -t AAAA zjkccb$i$j.cc +noall +answer
#     done
#done
#} &

{
for i in $list
do
   for j in $list
   do
       for k in $list
       do 
         #echo "zjkccb$i$j$k"
         dig +nocmd  -t A zjkccb$i$j$k.cc +noall +answer
         dig  +nocmd -t AAAA zjkccb$i$j$k.cc +noall +answer
      done
   done 
done 
} &

wait
