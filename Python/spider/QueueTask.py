#!/usr/bin/env python
#-*- encoding: utf-8 -*-
#author :liufapeng 
#Date : 2016-12-31
#version 2.1
import Queue
import time
import threading
import datetime
import logging
import logging.config
import BlogSpider


logging.config.fileConfig('logging.conf')
logger = logging.getLogger('bloglog')

MaxThread = 1

class ThreadTask(threading.Thread):
    '''
    task thread class inherit thread class,overwrite init and run function
    '''
    def __init__(self, queue, name, savefile):
        threading.Thread.__init__(self)
        self.queue = queue
        self.name = name
        self.savefile = savefile

        
    def run(self):
        while True:
            #get url from queue
            url = self.queue.get()     
            #to do task
            #print 'thread %s [%s]: %s'%(self.name, datetime.datetime.now(), url)
            BlogSpider.getBlogUrl(url, self.savefile)
            #to declare queue job is done
            self.queue.task_done()


def loadTask(urlfile):
    '''
    load tasks from a urllist file
    '''
    q = Queue.Queue()
    with open(urlfile,'r') as f:
        lines = f.readlines()
    for it in lines:
        #left out '\n'
        it = it.replace('\n', '')
        q.put(it)
    return q


def QueueTask(urlfile, savefile, separate = False):
    '''
    carry out queue task by threads
    '''
    queue = loadTask(urlfile)
    if (not queue.empty()):
        logger.info('all begin...')
        #create a pool of threads,and pass them queue instance
        for i in range(MaxThread):
            if separate:
                t = ThreadTask(queue, i, savefile+str(i)+'.txt')
            else:
                t = ThreadTask(queue, i, savefile)
            t.setDaemon(True)
            t.start()
        #waitting for the queue until everything has been done     
        queue.join()
        logger.info('all done...')
    else:
        print 'task queue is empty.'
        
if __name__ == '__main__':
    filename = './testlist.txt'
    savefile = './bloglist.txt'
    QueueTask(filename, savefile)
