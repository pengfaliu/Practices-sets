#!/usr/bin/python 
#coding:utf8
# snowflake algorithm
# liufapeng
# date: 2020-4-11
'''
    * Twitter_Snowflake<br>
    * SnowFlake的结构如下(每部分用-分开):<br>
    * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
    * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
    * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
    * 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
    * 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId<br>
    * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
    * 加起来刚好64位，为一个Long型。<br>
    * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
    */
'''

import time
import logging
#from .exceptions import InvalidSystemClock


# 64 位ID的划分

WORKER_ID_BITS = 5
DATACENTER_ID_BITS =5 
SEQUENCE_BITS = 12 



# 最大取值计算
MAX_WORKER_ID = -1 ^ (-1 << WORKER_ID_BITS) # 2**5-1
MAX_DATACENTER_ID = -1 ^ (-1 << DATACENTER_ID_BITS) #2**5-1

# 移位偏移计算 
WORKER_ID_SHIFT = SEQUENCE_BITS
DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS
TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS +DATACENTER_ID_BITS

# 序号循环掩码
SEQUENCE_MASK = -1 ^ (-1  << SEQUENCE_BITS)

# Twitter 元年时间

TWEPOCH = 1288834974657

logger = logging.getLogger('flask.app')

########################################################################
class IdWorker(object):
    """generate ID number"""

    #----------------------------------------------------------------------
    def __init__(self,datacenter_id,worker_id,sequence=0):
        """Constructor
        
        initial
        : param datacenter_id 数据中心（机器区域ID）
        : param worker_id : machine ID
        : param sequence : first number
        
        """
        # sanity check 
        if worker_id > MAX_WORKER_ID or worker_id < 0:
            raise ValueError('worker_id value overflow')
        
        if datacenter_id > MAX_DATACENTER_ID or datacenter_id < 0 :
            raise ValueError('datacenter_id  value overflow')
        
        self.worker_id = worker_id
        self.datacenter_id = datacenter_id
        self.sequence = sequence
        
        self.last_timestamp = -1  # 上次计算的时间戳
        
    def _gen_timestamp(self):
        """
        generate int timestamps
        :return :int timestamp
        """
        return int(time.time()*1000)
    
    def get_id(self):
        
        """
        get new id 
        :return new id 
        """
        timestamp = self._gen_timestamp()
        
        #clock back 
        if timestamp < self.last_timestamp:
            logger.error('clock is moving backwards.Rejecting requests unitl {}'.format(self.last_timestamp))
            raise InvalidSystemClock
        
        if timestamp == self.last_timestamp:
            self.sequence = (self.sequence + 1) & SEQUENCE_MASK
            if self.sequence == 0:
                timestamp = self._til_next_mills(self.last_timestamp)
        else:
            self.sequence == 0
            
        self.last_timestamp = timestamp
        
        new_id = ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT) | (self.datacenter_id << DATACENTER_ID_SHIFT) | \
            (self.worker_id << WORKER_ID_SHIFT) | self.sequence
        
        return new_id
    

    #----------------------------------------------------------------------
    def _til_next_mills(self,last_timestamp):
        
        """
        wait time until next second
        """
        timstamp = self._gen_timestamp()
        while timstamp <= last_timestamp:
            timstamp = self._gen_timestamp()
        return timstamp
    

if __name__ == "__main__":
    worker = IdWorker(31,31,0)
    for i in xrange(1,10000):
        print(worker.get_id())
        #print(bin(worker.get_id()))
        
            
            
        
    
    