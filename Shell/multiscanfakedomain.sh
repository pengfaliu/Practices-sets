#!/bin/bash

tmpfile='./tmp.txt'
Date=`date`
datestr=`date +%Y-%m-%d-%H%M%S`
list='a b c d e f g h i j k l m n o p q r s t u v w x y z 0 1 2 3 4 5 6 7 8 9'

process_num=50
validomain="./$datestr-vald.txt"
scheme='http://'

####
TMPFIFO=/tmp/$$.fifo
mkfifo $TMPFIFO
exec 5<>${TMPFIFO}
rm -rf ${TMPFIFO}  
for((i=1;i<=$process_num;i++))
do
   echo              
done >&5             
##### 

##### 
TMPFIF6=/tmp/$$-6.fifo
mkfifo $TMPFIF6
exec 6<>${TMPFIF6}
rm -rf ${TMPFIF6} 
for((i=1;i<=$process_num;i++))
do
   echo              
done >&6
####
function checkvalid()
{
     local tmp=`curl -IL -m 5 -s -w "%{http_code}" -o /dev/null $scheme$1`
     if [[ "$tmp" -eq "200" ]];then
        echo $1 >> $validomain
     fi
}

function checkresult() 
{
    if [[ -z "$s1" ]];then
       local tmpd=`echo $1|grep -v ';;'`
       if [[ -n $tmpd ]];then
           local tmpf=`echo $tmpd |awk '{print $1}'`
           checkvalid $tmpf
       fi 
    fi
}

function singleChar()
{ 
    for i in $list
    do 
      local tmp=`dig +nocmd  -t A zjkccb$i.cc +noall +answer`
      checkresult $tmp
      local tmp=`dig  +nocmd -t AAAA zjkccb$i.cc +noall +answer`
      checkresult $tmp
    done
} 

function doubleChar()
{
    for i in $list
      do 
        for j in $list
         do 
             local tmp=`dig +nocmd  -t A zjkccb$i$j.cc +noall +answer`
             checkresult $tmp
             local tmp=`dig  +nocmd -t AAAA zjkccb$i$j.cc +noall +answer`
             checkresult $tmp

         done
    done
} 

function threeChar()
{
     for i in $list
     do
        for j in $list
        do
            for k in $list
            do 
              read -u5
              {
              #echo "zjkccb$i$j$k"
              local tmp=`dig +nocmd  -t A zjkccb$i$j$k.cc +noall +answer`
              checkresult $tmp
              local tmp=`dig  +nocmd -t AAAA zjkccb$i$j$k.cc +noall +answer`
              checkresult $tmp
              echo "" >&5
              } & 
           done
        done 
     done 
} 

function zjkxxdoubleChar()
{ ## zjkxx.cc
    for i in $list
      do 
        for j in $list
         do 
             read -u6
             {
             local tmp=`dig +nocmd  -t A zjk$i$j.cc +noall +answer`
             checkresult $tmp
             local tmp=`dig  +nocmd -t AAAA zjk$i$j.cc +noall +answer`
             checkresult $tmp
             } &
         done
    done
} 

trap 'onCtrlC' INT
function onCtrlC () {
    echo 'Ctrl+C is captured'
    #kill 0
    kill $$;

}

function main()
{
      #singleChar  &
      #doubleChar  &
      #threeChar 
      zjkxxdoubleChar 
      exit 0
}
echo $Date >$validomain
main
#wait
exec 5>&-
exec 6>&-
exit 0
