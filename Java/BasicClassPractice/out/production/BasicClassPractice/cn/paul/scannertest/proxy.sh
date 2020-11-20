#!/usr/bin/env bash
# author liufapeng
# date 2020-11-18
# 这是一个用于配置mac地址系统级ssh socket隧道代理。
# $PATTERN_PRO_ID_NAME 是ssh远程主机的IP地址和用户名连接字符串
# $PATTERN_LOCAL_IP_PORT 是启用本地IP和端口串的连接串
# $SSH_PORT ssh远程主机的端口
# $GET_PID_COMMAND  查出mac系统中ssh socket代理地址在本地运行的进程号
# $WIFINAME 指macos的 wi-fi接口名
# 本脚本仅适用于mac os全局走ssh socket代理或单独启动代理,手动配置浏览器的小伙伴,此程序 \
#     不会自动更改浏览器的代理设置,如果想更改浏览器代理设置,请手动吧。

# connection information
PATTERN_PRO_ID_NAME="root@192.168.255.3"
PATTERN_LOCAL_IP_PORT="127.0.0.1:1080"
SSH_PORT=22

#filter
SLEF_GREP="grep"
GET_PID_COMMAND="$(ps -ef |grep $PATTERN_PRO_ID_NAME|grep -v $SLEF_GREP|awk '{printf $2}')"

#WIFI variable
WIFINAME="Wi-fi"
GLOBAL_SYS_PROXY_STATE=1  #系统级全局sock代理开关,默认全局代理是打开的,0:关闭,1:打开
SET_PROXY_ON_WIFI_INTERFACE=1
UNSET_PROXY_ON_WIFI_INTERFACE=0
GET_PROXY_INFO_STATE_ON_WIFI_INTERFACE=2
WIFI_PRXOY_SWITCH_ON="on"
WIFI_PROXY_SWITCH_OFF="off"

# state variable
RUNNING_STATE=1
NORMAL_STATE=0


helpMessage () {
    echo -e "\n 语法: $(basename ${BASH_SOURCE[0]})  [command] [switch] \r
 Usage: $(basename ${BASH_SOURCE[0]})  start [0|1]
        $(basename ${BASH_SOURCE[0]})  [stop|status|help]\n
 Default:
        $(basename ${BASH_SOURCE[0]})  start  1\n
 Example:
        $(basename ${BASH_SOURCE[0]})  start    #启动ssh ocket代理代理,并打开操作系统的全局代理
        $(basename ${BASH_SOURCE[0]})  start  1 #启动ssh ocket代理代理,并打开操作系统的全局代理(同上)
        $(basename ${BASH_SOURCE[0]})  stop     #关闭ssh socket代理,并关闭操作系统的全局代理
        $(basename ${BASH_SOURCE[0]})  status   #查看当前ssh运行状态及代理配置状态
        $(basename ${BASH_SOURCE[0]})  help     #获取此帮助\n
 注:此程序对于操作系统的全局代理的操作默认是打开的\n"

}

globalSysProxyErrMessage() {
        echo "系统代理开关参数错误,请核实!"
        helpMessage
}

configExploerProxyPromptMessage (){
     echo "现在请去手动配置浏览器代理吧,sock 本地代理信息如下:"
     echo $PATTERN_LOCAL_IP_PORT
}

wifiProxyOperation() {
    local action=$1  # 1:set|0:unset|2:state
    local switch=$2 #开关状态 on|off
    local ip=$(echo $PATTERN_LOCAL_IP_PORT|cut -d ':' -f 1)
    local port=$(echo $PATTERN_LOCAL_IP_PORT|cut -d ':' -f 2)
    local networksetup='networksetup'

    if [[ $action -eq $SET_PROXY_ON_WIFI_INTERFACE ]] && [[ $switch == $WIFI_PRXOY_SWITCH_ON ]];then ##设置全局代理
        #设置sock的代理
        $networksetup  -setsocksfirewallproxy $WIFINAME $ip $port
        $networksetup -setsocksfirewallproxystate $WIFINAME $switch

    elif  [[ $action -eq $UNSET_PROXY_ON_WIFI_INTERFACE ]] && [[ $switch == $WIFI_PROXY_SWITCH_OFF ]] ; then ##关闭全局代理
        #关闭sock代理
        $networksetup -setsocksfirewallproxystate $WIFINAME $switch

    elif [[ $action -eq $GET_PROXY_INFO_STATE_ON_WIFI_INTERFACE ]]; then ##获取状态
        #获取sock 代理状态
        $networksetup -getsocksfirewallproxy $WIFINAME
    else
        echo "$action or $switch 参数错误"
    fi
}

start() {
    if [[ $GLOBAL_SYS_PROXY_STATE -eq 0 ]]; then
        ssh -Nf -D $PATTERN_LOCAL_IP_PORT $PATTERN_PRO_ID_NAME -p $SSH_PORT
        configExploerProxyPromptMessage
    elif [[ $GLOBAL_SYS_PROXY_STATE -eq 1 ]];then
        ssh -Nf -D $PATTERN_LOCAL_IP_PORT $PATTERN_PRO_ID_NAME -p $SSH_PORT
        wifiProxyOperation $SET_PROXY_ON_WIFI_INTERFACE $WIFI_PRXOY_SWITCH_ON # 在wifi网卡上打开操作系统级代理
     else
        globalSysProxyErrMessage
    fi
}

stop() {
    local pid=$GET_PID_COMMAND
    wifiProxyOperation $UNSET_PROXY_ON_WIFI_INTERFACE $WIFI_PROXY_SWITCH_OFF # 关闭wifi网卡上的操作系统级代理
    echo "pid is $pid"
    kill -s 3 ${pid} #删除代理进程
}

status() {
    pid=$GET_PID_COMMAND
    if [[ $pid ]] #pid为非空是为真。
    then
        echo "--------------"
        echo "|$PATTERN_PRO_ID_NAME is running,pid is $pid"
        echo "--------------"
        echo "|$WIFINAME sock 网络代理信息如下:"
        wifiProxyOperation $GET_PROXY_INFO_STATE_ON_WIFI_INTERFACE #显示sock 当前代理状态
        echo "--------------"
        return $RUNNING_STATE
    else
        echo -e "$PATTERN_PRO_ID_NAME is not running."
        echo "--------------"
        wifiProxyOperation $GET_PROXY_INFO_STATE_ON_WIFI_INTERFACE #显示sock 当前代理状态
        return $NORMAL_STATE
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
               echo
               exit $NORMAL_STATE
            fi
            ;;
       stop)
            status
            if [[ $? == 0 ]] #没有运行直接
	        then
	           echo "Not running...,Nothing to do"
	           echo
	           exit $NORMAL_STATE
            else
                echo "Now stopping..."
                stop
            fi
            ;;
       status)
            status
            ;;
       help)
           helpMessage
           ;;
       *)
           helpMessage
            ;;
    esac
}

### Entry point ###

if [[ -z  $2 ]];then
    main $1 $GLOBAL_SYS_PROXY_STATE
elif [[ ! -z $2 ]];then
    GLOBAL_SYS_PROXY_STATE=$2 # 不使用全局系统代理
    main $1 $GLOBAL_SYS_PROXY_STATE
fi