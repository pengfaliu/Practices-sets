#!/usr/bin/env bash

PATTERN_PRO_ID_NAME="test@47.244.228.207"
PATTERN_LOCAL_IP_PORT="127.0.0.1:8080"
PORT=22
SLEF_GREP="grep"
GET_PID_COMMAND="$(ps -ef |grep $PATTERN_PRO_ID_NAME|grep -v $SLEF_GREP|awk '{printf $2}')"

start() {
    ssh -Nf -D $PATTERN_LOCAL_IP_PORT $PATTERN_PRO_ID_NAME -p $PORT
}

stop() {
    local pid=$GET_PID_COMMAND
    echo "pid is $pid"
    kill -s 3 ${pid}
}

status() {
    pid=$GET_PID_COMMAND
    if [[ $pid ]] #pid为非空是为真。
    then
        echo "$PATTERN_PRO_ID_NAME is running,pid is $pid"
        return 1
    else
        echo -e "$PATTERN_PRO_ID_NAME is not running."
        return 0
    fi
}

main() {
    case $1 in
       start)
            status
            if [[ $? == 0 ]] #没有运行才会启动
	        then
	           echo "Now starting..."
               start
            else
               echo  "Nothing to do"
               exit 0
            fi
            ;;
       stop)
            status
            if [[ $? == 0 ]] #没有运行直接
	        then
	           echo "Not running...,Nothing to do"
	           exit 0
            else
                echo "Now stopping..."
                stop
            fi
            ;;
       status)
            status
            ;;
       *)
           echo "start|stop|status should be give!"
    esac
}

main $1
