#!/bin/bash

function _create_pipe()
{
    _PROCESS_PIPE_NAME=$(_get_uid)

    mkfifo ${_PROCESS_PIPE_NAME}
    eval exec "${_PROCESS_PIPE_ID}""<>${_PROCESS_PIPE_NAME}"

    for ((i=0; i < $_PROCESS_NUM; i++))
    do
        echo -ne "\n" 1>&${_PROCESS_PIPE_ID}
    done
}

function process_run()
{
    cmd=$1
    if [ -z "$cmd" ]; then
        echo "please input command to run"
        _delete_pipe    
        exit 1
    fi

    _process_get
    {
        $cmd
        _process_post
    }&
}

function Print
{
    echo "Hello World!"
}

trap Print SIGKILL

function _clean_up
{
    _delete_pipe

    kill 0
    kill -9 $$
}

trap _clean_up SIGINT SIGHUP SIGTERM SIGKILL
