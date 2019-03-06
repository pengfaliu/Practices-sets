#-*- encoding: utf-8 -*-
#author : rayment
#CreateDate : 2012-07-19
#version 2.0
import re
import urllib2
import logging


logger = logging.getLogger('bloglog.spider')

def isLoadUsefulUrl(strhtml):
    '''
    check comment url and vistor url whether load
    1)comment url contain element
    re = <div class="commentsContantsTxt">
    2)visitor url contain element
    re = <div class="ptList visitorList"> or
    re = a id="linkVisited_[0-9]*"
    '''
    commentre = re.compile(r'<div class="commentsContantsTxt">', re.I)
    visitorre = re.compile(r'a id="linkVisited_[0-9]*"', re.I)
    ret = 0
    commentfind = commentre.findall(strhtml)
    if commentfind:
        ret = 1
        for rs1 in commentfind:
            print rs1
    visitorfind = visitorre.findall(strhtml)
    if visitorfind:
        ret = 2
        for rs2 in visitorfind:
            print rs2
    return ret


def getBlogUrl(websize, savefile):
    '''
    parse all sinablog url from a assign html
    sinablog url include two rule:
    1)http://blog.sina.com.cn/xxxxx
    2)http://blog.sina.com.cn/x/xxxxxx
    re = http://blog.sina.com.cn/[\w]+[/\d]*
    '''
    urlre = re.compile(r'(http://[^/\\]+)', re.I)
    hrefre = re.compile(r'<a href=".*?<\/a>', re.I)
    blogre = re.compile(r'http://blog.sina.com.cn/[\w]+[/\d]*', re.I)
    filterre = re.compile(r'.htm?|.xml|</p>|http://blog.sina.com.cn/[\w]+/[\w]+/', re.I)
    urlmatch = urlre.match(websize)
    if not urlmatch:
        #print '%s is not a correct url.'%websize
        logger.info('%s is not a correct url.'%websize)
    else:
        try:
            urllist = []
            fd = urllib2.urlopen(websize)
            content =fd.read()
            #print '\nConnetion %s success...'%(websize)
            logger.info('Connetion %s success...'%(websize))
            hrefs = hrefre.findall(content)
            for href in hrefs:
                splits = href.split(' ')
                if len(splits) != 1:
                    href = splits[1]
                #get text of href tag
                matches = re.match('href="(.*)"', href)
                if matches is not None:
                    url = matches.group(1)
                    if blogre.match(url) is not None:
                        if filterre.findall(url):
                            pass
                        else:
                            urllist.append(url)
            saveFile(filterDuplicateData(urllist), savefile)           
        except Exception, error:
            #print error
            logger.info(error)


def filterDuplicateData(ls):
    '''
    filter duplicate data
    '''
    newls = []
    for data in ls:  
        if not data in newls:  
            newls.append(data)
    '''
    newls = list(set(ls))
    newls.sort(key = ls.index)
    '''
    #print 'Search blog url------>'
    logger.info('Search blog url------>')
    num = 1
    for item in newls:
        #print '%d: %s'%(num, item)
        logger.info('%d: %s'%(num, item))
        num = num + 1
    return newls


def saveFile(bloglist, savefile):
    '''
    save urllist in a text file
    '''
    if bloglist and (len(bloglist) > 0):
        with open(savefile, 'rb') as fr:
            temp = fr.read()
        with open(savefile, 'ab') as fw:
            logger.info('Add blog url------>')
            #print 'Add blog url------>'
            for it in bloglist:
                it = it.strip()
                if it not in temp:
                    #print it
                    logger.info(it)
                    fw.write(it+'\n')
    else:
        #print 'There are not blog list.'
        logger.info('There are not blog list.')
        


if __name__ == '__main__':
    websize ='http://blog.sina.com.cn/raymentblog'
    savefile = 'D:\BlogSpider\\bloglist.txt'
    getBlogUrl(websize, savefile)