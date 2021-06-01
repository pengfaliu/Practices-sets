#!/bin/bash


tmpfile='./tmp.txt'

for i in `seq 97 122`
do
  k=`echo $i | awk '{printf("%c", $1)}'`
  
  dig +nocmd  -t A zjkccb$k.cc +noall +answer 
  dig  +nocmd -t AAAA zjkccb$k.cc +noall +answer

  for j in `seq 1 9`
  do 
      #echo "$k+$j"
      dig  +nocmd -t A zjkccb$j.cc +noall +answer 
      dig  +nocmd -t AAAA zjkccb$j.cc +noall +answer 
      dig  +nocmd -t A zjkccb$k$j.cc +noall +answer
      dig  +nocmd -t AAAA zjkccb$k$j.cc +noall +answer 
  done
done 

for i in `seq 97 122`
do
  k=`echo $i | awk '{printf("%c", $1)}'`
  for j in `seq 97 122`
  do 
      p=`echo $j | awk '{printf("%c", $1)}'`
      #echo "$k+$p"
      dig  +nocmd -t A zjkccb$k$p.cc +noall +answer
      dig  +nocmd -t AAAA zjkccb$k$p.cc +noall +answer 
  done
done 

